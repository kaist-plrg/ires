const argv = require('yargs')
  .scriptName('unicode-gen')
  .usage('Usage: $0 -p [property-name] -o [output-file]')
  .example(
    '$0 -p ID_Start -o id_start.json',
  )
  .option('property', {
    alias: 'p',
    describe: 'The property name of Unicode',
    type: 'string',
    nargs: 1,
  })
  .option('out', {
    alias: 'o',
    describe: 'The output filename',
    type: 'unicode.json',
  })
  .alias('help', 'h')
  .version('1.0.0')
  .alias('version', 'v')
  .argv;

let property = argv.property
let filename = argv.out;

module.exports = { property, filename };
