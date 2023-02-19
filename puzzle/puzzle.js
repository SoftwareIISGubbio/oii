#!/usr/bin/env node

const { exit } = require('process');

fs = require('fs')

start = Date.now();
let linee = fs.readFileSync('puzzle.txt', 'utf8').split("\n");
let i = 0
let iniziato = false
let tuple = [];
for(let i=0; i<linee.length; i++){
    linee[i] = linee[i].trim()
    if(iniziato){
        x = linee[i].split(" ");
        if(x.length>3){ // ma che accidenti legge in fondo al file??
            tuple.push( [Number(x[0]), Number(x[1]), Number(x[2]), Number(x[3]) , x.length==4 ? '' : x[4]] );
        }
    }
    if(linee[i]=="h"){
        h = Number(linee[i+1]);
        i++;
    }
    if(linee[i]=="w"){
        w = Number(linee[i+1]) 
        i=i+1
        iniziato = true
    }
}
stop = Date.now();
console.log("file parsing: ",(stop-start));

function lCerca(tuple,pos,val){
    for(let index=0; index<tuple.length; index++){
        if(tuple[index][pos]==val){
            return index;
        }
    }
    return -1;
}

function bCerca(tuple,pos,val){
    let inizio=0
    let fine=tuple.length-1
    while(inizio<=fine){
        medio = Math.floor( (inizio+fine)/2 )
        if(tuple[medio][pos] == val){
            return medio;
        } else {
            if(tuple[medio][pos] > val){
                fine = medio-1
            }else{
                inizio = medio+1
            }
        }
    }
    return -1
}

let mappa = [];
for(let i=0; i<w; i++){
    mappa.push([]);
}

numbers = [1, 2, 3];
numbersCopy = [...numbers];

start = Date.now();
oUnder = [...tuple];
oUnder.sort((e1,e2) => e1[1]-e2[1] );
oRight = [...tuple];
oRight.sort((e1,e2) => e1[3]-e2[3] );
oUpper = [...tuple];
oUpper.sort((e1,e2) => e1[0]-e2[0] );
stop = Date.now();
console.log("copy & sort: ",(stop-start));

ricerca = bCerca
console.log( "ricerca: "+ricerca.name );

start =  Date.now();
tuple.forEach( candidato => {
    if(ricerca(oRight,3,candidato[2])==-1 && ricerca(oUnder,1,candidato[0])==-1){
        mappa[0].push(candidato);
    }
});
stop = Date.now();
console.log("angolo: ",(stop-start));

start =  Date.now();
for(let i=1; i<=w-1; i++){
    tuple.forEach( candidato => {
        if(candidato[2] == mappa[0][i-1][3] && ricerca(oUnder,1,candidato[0])==-1){
            mappa[0].push( candidato );
        }
    });
}
stop = Date.now();
console.log("linea zero: ",(stop-start));

start =  Date.now();
for(let iRiga=1 ; iRiga<=h-1 ; iRiga++){
    for(let iColonna=0; iColonna<=w-1 ; iColonna++){
        mappa[iRiga].push( oUpper[ ricerca(oUpper,0,mappa[iRiga-1][iColonna][1]) ] );
    }
}
stop = Date.now();
console.log("mappa: ",(stop-start));

let chiave = "";
for(let iR=0; iR<=h-1; iR++){
    for(let iC=0; iC<=w-1; iC++){
        chiave+=mappa[iR][iC][4];
    }
}
console.log(chiave);
