const fs = require('fs');
const assert = require('assert');
const jsonDiff = require('json-diff');

const parser = require('../src/es2021');
const EXT = '.js';
const EXT_LEN = EXT.length;
const RESET = '\033[0m';

describe('es2021', function() {
  for (filename of fs.readdirSync('./test/js')) {
    if (!filename.endsWith(EXT)) continue;
    let name = filename.slice(0, -EXT_LEN);
    let jsPath = `./test/js/${name}.js`;
    let astPath = `./test/ast/${name}.json`;
    if (fs.existsSync(astPath)) {
      it(`should successfully parse ${name}.js`, function() {
        let parsed = parser.parseFile(jsPath);
        let answer = JSON.parse(fs.readFileSync(astPath, 'utf8'));
        let diff = jsonDiff.diffString(parsed, answer);
        if (diff !== '') assert.fail('\n\n' + RESET + diff);
      });
    }
  }
});
