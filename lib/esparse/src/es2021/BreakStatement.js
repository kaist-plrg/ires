const Node = require('../Node');

let BreakStatement = (Yield, Await) => (given) => {
  Yield, Await, given;
  Node.TODO('BreakStatement');
}

module.exports = BreakStatement;
