const fs = require('fs')

class Boat {
    constructor() {
        this.horizontal = 0;
        this.vertical = 0;
    }
    incHorizontal(x) { this.horizontal = this.horizontal + x }
    incVertical(x) { this.vertical = this.vertical + x }
    position() { return `h: ${this.horizontal} v: ${this.vertical}` }
    result() { return this.horizontal * this.vertical }
};

fs.readFile('input.txt', 'utf8' , (err, data) => {
    if (err) {
      console.error(err)
      return
    }
    var boat = new Boat();
    var rows = data.toString().split("\n");
    var counter = 1;
    for(i in rows) {
        var command = rows[i].split(/\s+/);
        if (command.length == 2) {
            if (command[0] == 'forward') {
                boat.incHorizontal(parseInt(command[1]));
            } else if (command[0] == 'down') {
                boat.incVertical(parseInt(command[1]));
            } else if (command[0] == 'up') {
                boat.incVertical(-parseInt(command[1]));
            }
            // var str = command[0] + '-' + command[1];
            // console.log(counter + ': ' + str);
            counter++;
            console.log(counter + ': ' + boat.position());
        }
    }
    console.log('Position: ' + boat.position() + ' Result: ' + boat.result());
  });

