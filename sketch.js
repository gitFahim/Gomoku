let board = [
  ['O', 'O', '', '', '', '', '', '', '', 'X'],
  ['', '', '', '', '', '', '', '', '', ''],
  ['', '', '', '', '', '', '', '', '', ''],
  ['', '', '', '', 'O', '', '', 'X', 'O', ''],
  ['', '', '', '', '', '', '', '', '', ''],
  ['', '', '', '', '', '', '', '', '', 'X'],
  ['', '', '', '', '', '', '', '', '', ''],
  ['', '', '', '', '', '', '', '', '', ''],
  ['', '', '', '', '', '', '', '', '', ''],
  ['X', 'X', '', '', '', '', '', '', '', ''],
];

let players = ['X', 'O'];

function setup() {
  createCanvas(400, 400);
}

function draw() {
  background(255);
  let w = width / 10;
  let h = height / 10;
  
  line(w,0,w,height);
  line(w*2,0,w*2,height);
  line(w*3,0,w*3,height);
  line(w*4,0,w*4,height);
  line(w*5,0,w*5,height);
  
  for(let p=1; p<10; p++) {
      line(w*p, 0, w*p, height);
      line(0,h*p,width,h*p);
  }
  
  for(let j=0; j<10; j++) {
      for(let i=0; i<10; i++){
        let x = w * j + w/2;
        let y = h * i + h/2;
        let spot = board[i][j];
        textSize(22);
        strokeWeight(2);
        if(spot == players[1]) {
          noFill();
          ellipse(x,y,w/2);
        }
        else if(spot == players[0]) {
          fill(51);
          ellipse(x,y,w/2);
        }
    }      
  }
  
}

function equals3(a, b, c) {
  return a == b && b == c && a != '';
}

function checkWinner() {
  let winner = null;

  // horizontal
  for (let i = 0; i < 3; i++) {
    if (equals3(board[i][0], board[i][1], board[i][2])) {
      winner = board[i][0];
    }
  }

  // Vertical
  for (let i = 0; i < 3; i++) {
    if (equals3(board[0][i], board[1][i], board[2][i])) {
      winner = board[0][i];
    }
  }

  // Diagonal
  if (equals3(board[0][0], board[1][1], board[2][2])) {
    winner = board[0][0];
  }
  if (equals3(board[2][0], board[1][1], board[0][2])) {
    winner = board[2][0];
  }

  if (winner == null && available.length == 0) {
    return 'tie';
  } else {
    return winner;
  }
}

function nextTurn() {
  let index = floor(random(available.length));
  let spot = available.splice(index, 1)[0];
  let i = spot[0];
  let j = spot[1];
  board[i][j] = players[currentPlayer];
  currentPlayer = (currentPlayer + 1) % players.length;
}

// function mousePressed() {
//   nextTurn();
// }

function draw() {
  background(255);
  let w = width / 3;
  let h = height / 3;
  strokeWeight(4);

  line(w, 0, w, height);
  line(w * 2, 0, w * 2, height);
  line(0, h, width, h);
  line(0, h * 2, width, h * 2);

  for (let j = 0; j < 3; j++) {
    for (let i = 0; i < 3; i++) {
      let x = w * i + w / 2;
      let y = h * j + h / 2;
      let spot = board[i][j];
      textSize(32);
      let r = w / 4;
      if (spot == players[1]) {
        noFill();
        ellipse(x, y, r * 2);
      } else if (spot == players[0]) {
        line(x - r, y - r, x + r, y + r);
        line(x + r, y - r, x - r, y + r);
      }
    }
  }

  let result = checkWinner();
  if (result != null) {
    noLoop();
    let resultP = createP('');
    resultP.style('font-size', '32pt');
    if (result == 'tie') {
      resultP.html('Tie!');
    } else {
      resultP.html(`${result} wins!`);
    }
  } else {
    nextTurn();
  }
}