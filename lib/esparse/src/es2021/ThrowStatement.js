const Node = require('../Node');

let ThrowStatement = (Yield, Await) => (given) => {
  Yield, Await, given;
  Node.TODO('ThrowStatement');
}

module.exports = ThrowStatement;
