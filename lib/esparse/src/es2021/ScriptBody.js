const Node = require('../Node');

const StatementList = require('./StatementList');

// ScriptBody : StatementList[~Yield, ~Await, ~Return]
let ScriptBody = (given) => {
  let list = StatementList(false, false, false)(given)
  return new Node('ScriptBody', given, 0, [list]);
}

module.exports = ScriptBody;
