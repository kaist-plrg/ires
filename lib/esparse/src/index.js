const fs = require('fs');
const { target, filename } = require('./args');

// loading parser
const parser = (() => {
  try {
    return require(`./${target}`);
  } catch (e) {
    console.error(`[NotSupport] ECMAScript version: ${target}`);
    process.exit(1);
  }
})();

const code = fs.readFileSync(filename, 'utf8');
console.log("- given JavaScript program");
console.log("----------------------------------------");
console.log(code);
console.log("----------------------------------------");

// parse a given file using acorn
let acornAst = parser.acornParse(code);
console.log("- AST produced by `acorn`");
console.log("----------------------------------------");
console.log(JSON.stringify(acornAst, null, 2));
console.log("----------------------------------------");

// transpile acorn AST to ECMAScript AST
const ast = parser.trans(acornAst);
console.log("- AST produced by `esparse`");
console.log("----------------------------------------");
console.log(JSON.stringify(ast, null, 2));
console.log("----------------------------------------");
