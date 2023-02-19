#!/usr/bin/python3
import time

print("🐍 python3 🐍")

# ============================== costruisco le tuple
start =  int( time.time() * 1000)
with open('puzzle.txt') as f:
    linee = f.readlines()
i = 0
iniziato = False
tuple = [];
while i < len(linee):
    linee[i] = linee[i].strip()
    if iniziato:
        x = linee[i].split(" ")
        tuple.append( [int(x[0]), int(x[1]), int(x[2]), int(x[3]) , '' if len(x)==4 else x[4]] )
    if linee[i]=="h":
        h = int(linee[i+1])
        i=i+1
    if linee[i]=="w":
        w = int(linee[i+1]) 
        i=i+1
        iniziato = True
    i=i+1
stop =  int( time.time() * 1000)
print (f"file parsing: {stop-start}")

def lCerca(tuple,pos,val):
    # https://realpython.com/python-enumerate/#using-pythons-enumerate
    for index, item in enumerate(tuple):
        if item[pos]==val:
            return index
    return -1

def bCerca(tuple,pos,val):
    inizio=0
    fine=len(tuple)-1
    while inizio<=fine:
        medio = int( (inizio+fine)/2 )
        if tuple[medio][pos] == val:
            return medio
        else:
            if tuple[medio][pos] > val:
                fine = medio-1
            else:
                inizio = medio+1
    return -1

mappa = []
for i in range(0, w): # FIXME mi sa w-1!
    mappa.append([])

# https://www.programiz.com/python-programming/methods/list/sort
start =  int( time.time() * 1000)
oUnder = tuple.copy()
oUnder.sort( key = (lambda elem : elem[1]) )
oRight = tuple.copy()
oRight.sort( key = (lambda elem : elem[3]) )
oUpper = tuple.copy()
oUpper.sort( key = (lambda elem : elem[0]) )
stop =  int( time.time() * 1000)
print (f"copy & sort: {stop-start}")

ricerca = bCerca
print( f"ricerca: {ricerca.__name__}" )

start =  int( time.time() * 1000)
for candidato in tuple:
    if ricerca(oRight,3,candidato[2])==-1 and ricerca(oUnder,1,candidato[0])==-1:
        mappa[0].append(candidato)
stop =  int( time.time() * 1000)
print (f"angolo: {stop-start}")

start =  int( time.time() * 1000)
for i in range(1,w-1):
    for candidato in tuple:
        if candidato[2] == mappa[0][i-1][3] and ricerca(oUnder,1,candidato[0])==-1:
            mappa[0].append( candidato )
stop =  int( time.time() * 1000)
print (f"linea zero: {stop-start}")

start =  int( time.time() * 1000)
for iRiga in range(1, h-1):
    for iColonna in range(0,w-1):
        mappa[iRiga].append( oUpper[ ricerca(oUpper,0,mappa[iRiga-1][iColonna][1]) ] )
stop =  int( time.time() * 1000)
print (f"mappa: {stop-start}")

for iR in range(0,h-1):
    for iC in range(0,w-1):
        print(mappa[iR][iC][4], end='')
print()