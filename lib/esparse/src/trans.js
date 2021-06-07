const acorn = require('acorn');
const fs = require('fs');

let ecmaVersion = 2021;

function acornParse(code) {
  try {
    return acorn.parse(code, { ecmaVersion });
  } catch (e) {
    console.error(`[SyntaxError] ${e}`);
    process.exit(1);
  }
}

function trans(acornAst) {
  const Script = require(`./es${ecmaVersion}/Script`);
  return Script(acornAst);
}

function parse(code) {
  const acornAst = acornParse(code);
  return trans(acornAst);
}

function parseFile(filename) {
  const code = fs.readFileSync(filename, 'utf8');
  return parse(code);
}

module.exports = {
  trans: trans,
  acornParse: acornParse,
  parse: parse,
  parseFile: parseFile
}
