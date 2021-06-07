const Node = require('../Node');

let AwaitExpression = (Yield) => (given) => {
  Yield, given;
  Node.TODO('AwaitExpression');
}

module.exports = AwaitExpression;
