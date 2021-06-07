const Node = require('../Node');

let BreakableStatement = (Yield, Await, Return) => (given) => {
  Yield, Await, Return, given;
  Node.TODO('BreakableStatement');
}

module.exports = BreakableStatement;
