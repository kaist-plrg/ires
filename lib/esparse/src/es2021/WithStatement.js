const Node = require('../Node');

let WithStatement = (Yield, Await, Return) => (given) => {
  Yield, Await, Return, given;
  Node.TODO('WithStatement');
}

module.exports = WithStatement;
