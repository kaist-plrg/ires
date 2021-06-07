const assert = require('assert');
const Node = require('../Node');
const ScriptBody = require('./ScriptBody');

// Script : ScriptBody_opt
let Script = (given) => {
  assert(given.sourceType === 'script');
  let body = null;
  if (given.body.length > 0) body = ScriptBody(given);
  return new Node('Script', given, 0, [body]);
}

module.exports = Script;
