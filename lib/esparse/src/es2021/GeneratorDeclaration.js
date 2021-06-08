const Node = require('../Node');

let GeneratorDeclaration = (Yield, Await, Default) => (given) => {
  Yield, Await, Default, given;
  Node.TODO('GeneratorDeclaration');
}

module.exports = GeneratorDeclaration;
