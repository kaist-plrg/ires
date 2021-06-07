const fs = require('fs');
const assert = require('assert');
const { check } = require('../src/checker');
const parser = require('../src/trans');
const EXT = '.js';
const EXT_LEN = EXT.length;
const RESET = '\033[0m';
const TEST_DIR = '../../tests';
const JS_DIR = `${TEST_DIR}/js`;
const AST_DIR = `${TEST_DIR}/ast`;

describe('trans', function() {
  let todoList = new Set();
  let notyet = 0;
  for (filename of fs.readdirSync(JS_DIR)) {
    if (!filename.endsWith(EXT)) continue;
    let name = filename.slice(0, -EXT_LEN);
    let jsPath = `${JS_DIR}/${name}.js`;
    let astPath = `${AST_DIR}/${name}.json`;
    if (fs.existsSync(astPath)) {
      let parsed;
      try {
        parsed = parser.parseFile(jsPath);
        it(`should successfully parse ${name}.js`, () => {
          try {
            let json = parsed.compress();
            let expected = JSON.parse(fs.readFileSync(astPath, 'utf8'));
            check(expected, json, 'this');
          } catch(e) {
            console.log(JSON.stringify(parsed, null, 2));
            throw e;
          }
        });
      } catch(e) {
        let msg = e.toString();
        notyet++;
        if (msg.startsWith('[TODO]')) todoList.add(msg);
        else throw e;
      }
    }
  }
  for (msg of todoList) {
    console.error(msg);
  }
  if (notyet > 0) console.error(`* ${notyet} tests are not yet passed.`);
});
