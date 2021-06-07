const Node = require('../Node');

let TryStatement = (Yield, Await, Return) => (given) => {
  Yield, Await, Return, given;
  Node.TODO('TryStatement');
}

module.exports = TryStatement;
