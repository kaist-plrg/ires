const Node = require('../Node');

let BlockStatement = (Yield, Await, Return) => (given) => {
  Yield, Await, Return, given;
  Node.TODO('BlockStatement');
}

module.exports = BlockStatement;
