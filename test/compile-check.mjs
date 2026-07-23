#!/usr/bin/env node
/**
 * compile-check.mjs — offline compile gate for the AndroidArchitectureRules fixtures.
 *
 * The fixtures in src/ reference Android / Room / Retrofit / OkHttp types that aren't in the
 * repo, so plain `javac src/**` fails with ~50 "cannot find symbol" errors. This gate compiles
 * src/ together with the minimal stub tree under stubs/ (androidx.lifecycle, androidx.room,
 * android.content, retrofit2, okhttp3, …) so the whole set type-checks with a stock JDK 8 —
 * no Gradle, no Android SDK, no network.
 *
 * Purpose: after you (or an LLM) fix a design-rule violation in src/, confirm the fix didn't
 * introduce a compilation failure — the compile counterpart to the srcML/XPath rule check.
 *
 * Because the fixtures' seeded violations are all *legal Java* (a missing no-arg ctor, an exposed
 * MutableLiveData, a missing execute() method — none are compile errors), the baseline compiles
 * with ZERO errors. So `--strict` returning non-zero means exactly "your change broke the build"
 * — no baseline diff needed (unlike CrowdCode, whose seeded violations are themselves compile errors).
 *
 * Usage:
 *   node test/compile-check.mjs            # compile, report error count, exit 0 (unless javac won't run)
 *   node test/compile-check.mjs --strict   # exit non-zero if there is ANY compile error   <-- the fix gate
 * JDK:  auto-detects javac (env JAVAC / JAVA_HOME → Eclipse Adoptium jdk-8* → `javac` on PATH).
 */

import { spawnSync } from "node:child_process";
import { existsSync, readdirSync, rmSync, mkdirSync, statSync } from "node:fs";
import { dirname, join, resolve } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const REPO_ROOT = resolve(__dirname, "..");
const OUT = join(REPO_ROOT, "build", "classes");
const STRICT = process.argv.slice(2).some((a) => a === "--strict" || a === "strict" || a === "compile-strict");

const C = { reset: "\x1b[0m", red: "\x1b[31m", green: "\x1b[32m", yellow: "\x1b[33m", dim: "\x1b[2m", bold: "\x1b[1m" };
const paint = (s, c) => `${c}${s}${C.reset}`;
const die = (m) => { console.error(paint(`\nSETUP ERROR: ${m}`, C.red)); process.exit(2); };

/* --------------------------------------------------------------------- locate javac */
function resolveJavac() {
  if (process.env.JAVAC) return process.env.JAVAC;
  if (process.env.JAVA_HOME) {
    const j = join(process.env.JAVA_HOME, "bin", process.platform === "win32" ? "javac.exe" : "javac");
    if (existsSync(j)) return j;
  }
  if (process.platform === "win32") {
    const base = join(process.env["ProgramFiles"] || "C:/Program Files", "Eclipse Adoptium");
    if (existsSync(base)) {
      const jdk8 = readdirSync(base).filter((d) => /jdk-8/.test(d)).sort().reverse()[0];
      if (jdk8) {
        const j = join(base, jdk8, "bin", "javac.exe");
        if (existsSync(j)) return j;
      }
    }
  }
  return "javac"; // fall back to PATH
}
const JAVAC = resolveJavac();

/* ------------------------------------------------------------------ gather sources */
function listJava(root) {
  const out = [];
  const walk = (d) => {
    for (const name of readdirSync(d)) {
      const p = join(d, name);
      if (statSync(p).isDirectory()) walk(p);
      else if (name.endsWith(".java")) out.push(p);
    }
  };
  if (existsSync(root)) walk(root);
  return out;
}

const srcFiles = listJava(join(REPO_ROOT, "src"));
const stubFiles = listJava(join(REPO_ROOT, "stubs"));
if (srcFiles.length === 0) die("no .java files found under src/");
if (stubFiles.length === 0) die("no .java files found under stubs/ — the gate needs the stub tree to resolve Android/Room/Retrofit types.");

/* ------------------------------------------------------------------------- compile */
rmSync(OUT, { recursive: true, force: true });
mkdirSync(OUT, { recursive: true });

console.log(paint("\nAndroidArchitectureRules — compile gate", C.bold));
console.log(paint(`javac : ${JAVAC}`, C.dim));
console.log(paint(`source: ${srcFiles.length} fixtures + ${stubFiles.length} stubs   mode: ${STRICT ? "strict" : "report"}`, C.dim));

const args = ["-encoding", "UTF-8", "-nowarn", "-d", OUT, ...srcFiles, ...stubFiles];
const r = spawnSync(JAVAC, args, { encoding: "utf8", maxBuffer: 64 * 1024 * 1024 });

if (r.error && r.error.code === "ENOENT") {
  die(`could not run javac at "${JAVAC}". Install a JDK 8 or set JAVAC / JAVA_HOME.`);
}
const output = `${r.stdout || ""}${r.stderr || ""}`;
// javac emits "<file>:<line>: error: <msg>"; count those lines.
const errorLines = (output.match(/^.*:\d+:\s*error:.*$/gm) || []);
const errorCount = errorLines.length;

console.log(paint(`\n${"═".repeat(52)}`, C.dim));
if (errorCount === 0 && (r.status === 0)) {
  console.log(paint(`✓ compile OK — 0 errors (${srcFiles.length + stubFiles.length} files)`, C.green + C.bold));
  process.exit(0);
}

// there are errors — show a trimmed view
console.log(paint(`✗ ${errorCount} compile error(s):`, C.red + C.bold));
for (const line of errorLines.slice(0, 12)) {
  console.log(paint(`  ${line.replace(REPO_ROOT + "\\", "").replace(REPO_ROOT + "/", "").trim()}`, C.red));
}
if (errorCount > 12) console.log(paint(`  … and ${errorCount - 12} more`, C.dim));

if (STRICT) {
  console.log(paint(`\nFAIL (strict): the change does not compile — a fix must not break the build.`, C.red));
  process.exit(1);
} else {
  console.log(paint(`\n(report mode: exit 0. Use --strict to fail the build on any error.)`, C.yellow));
  process.exit(0);
}
