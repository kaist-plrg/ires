const Node = require('../Node');

let ExpressionStatement = (Yield, Await) => (given) => {
  Yield, Await, given;
  Node.TODO('ExpressionStatement');
}

module.exports = ExpressionStatement;
