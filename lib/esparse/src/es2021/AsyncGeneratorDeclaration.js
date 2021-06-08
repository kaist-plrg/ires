const Node = require('../Node');

let AsyncGeneratorDeclaration = (Yield, Await, Default) => (given) => {
  Yield, Await, Default, given;
  Node.TODO('AsyncGeneratorDeclaration');
}

module.exports = AsyncGeneratorDeclaration;
