  .syntax unified
  .cpu cortex-m3
  .fpu softvfp
  .thumb
  
  .global  Main

Main:

  @ Follow the instructions in the handout for Assignment 1

  MUL R12, R2, R2
  MOV R0, #1

IncrementX:
  MOV R8, #0            //R8=count=i
  ADD R3, R3, #1        //R3==X
  MOV R11, R1
  MOV R7, #0            //boolean R7 = valueIsPresent;
  CMP R3, R12
  BGT FindSumOfFirstRow

CheckingIfAllIntegersUpToNSquaredArePresent:
  CMP R8, R12
  BEQ CheckingIfValueIsPresent
  LDR R4, [R11], #4                           //R4 = current value in magic square being compared to x
  ADD R8, R8, #1
  CMP R3, R4
  BEQ ValueIsPresentInSquare
  B   CheckingIfAllIntegersUpToNSquaredArePresent

ValueIsPresentInSquare:
  ADD R7, R7, #1
  B   CheckingIfAllIntegersUpToNSquaredArePresent

CheckingIfValueIsPresent:
  CMP R7, #1
  BNE NotAMagicSquare
  B   IncrementX



@seeing if rows, columns, diagonals are equal in sums

FindSumOfFirstRow:
  CMP R8, R2
  BEQ ResetRowCountVariable
  LDR R5, [R11], #4
  ADD R10, R10, R5
  ADD R8, R8, #1
  B FindSumOfFirstRow  

  //R10 has sum of first row
ResetRowCountVariable:
  MOV R4, #0          //will be used later to check if I have gone through all the rows
  MOV R11, R1

IncrementRowX:
  ADD R4, R4, #1      //R4, starting at 1, increments everytime I go through a row, so, when it's greater, I've scanned all the rows
  CMP R4, R2
  BGT ResetColumnCountVariables
  MOV R8, #0
  MOV R3, #0          //will compare R3 to sum of first row

CompareRows:
  CMP R8, R2
  BEQ CompareSumOfRow
  LDR R5, [R11], #4
  ADD R3, R3, R5    //R3 == sum of elements in row
  ADD R8, R8, #1
  B   CompareRows

CompareSumOfRow:
  CMP R3, R10
  BEQ IncrementRowX
  B   NotAMagicSquare


ResetColumnCountVariables:
  MOV R11, R1
  SUB R11, R11, #4
  MOV R4, #4
  MUL R9, R2, R4           //used to index into columns, r9 = row size x element size
  MOV R4, #0
  MOV R7, #0

GoToNextColumn:
  CMP R4, R2
  BEQ IsASemiMagicSquare
  ADD R4, R4, #1
  ADD R11, R11, #4
  MOV R8, #0
  MOV R6, #0
  MOV R3, #0                 //will compare R3 to sum of first row

CompareColumns:
  CMP R8, R2
  BEQ CompareSumOfColumn
  MUL R6, R6, R9             //if r6==2, accessing [x][2]
  LDR R5, [R11, R6]
  ADD R3, R3, R5
  ADD R8, R8, #1
  MOV R6, R8
  B   CompareColumns

CompareSumOfColumn:
  CMP R3, R10
  BEQ GoToNextColumn
  B   NotAMagicSquare

IsASemiMagicSquare:
  MOV R0, #1

ResetDiagonalCountVariables:
  MOV R4, #0
  MOV R11, R1
  MOV R8, #0
  ADD R3, R9, #4     //R3 indexes into rows
  MOV R12, #0    //R12 indexes into columns
  MOV R6, #0

  CMP R7, #0
  BEQ FindDescendingDiagonalSum
  SUB R3, R3, #8
  B  FindAscendingDiagonalSum

FindDescendingDiagonalSum:
  CMP R8, R2
  BEQ CheckDescendingDiagonalSum
  
  LDR R5, [R11, R6]
  ADD R4, R4, R5
  ADD R6, R6, R3

  ADD R8, R8, #1
  B   FindDescendingDiagonalSum

FindAscendingDiagonalSum:  //R6==incrementing (row) index, R7==decrementing(column) index
  CMP R8, R2
  BEQ CheckAscendingDiagonalSum

  ADD R6, R6, R3
  LDR R5, [R11, R6]

  ADD R4, R4, R5
  ADD R8, R8, #1
  B   FindAscendingDiagonalSum



CheckDescendingDiagonalSum:
  MOV R7, #1
  CMP R4, R10
  BEQ ResetDiagonalCountVariables
  B   End_Main

CheckAscendingDiagonalSum:
  CMP R4, R10
  BEQ IsAMagicSquare
  BNE End_Main



IsAMagicSquare:
  MOV R0, #2


//Associative magic square

  MOV R11, R1
  MUL R12, R2, R2
  //R5 = value closer to beginning
  //R6 = value closer to end
  LDR R5, [R11]

  MOV R4, #4
  MOV R3, #2
  UDIV R8, R12, R3
  

  MUL R2, R12, R4
  ADD R2, R2, R1
  SUB R2, R2, #4

  
  ADD R9, R12, #1
  MOV R10, #-1
  //R1 = address of closer to beginning
  //r2 = ''       '' ''    '' end

WhileLoop:
  CMP R8, #0
  BGT IsAssociativeMagicSquare
  SUB R8, R8, #1

  LDR R5, [R1], #4
  LDR R6, [R2], #-4

  ADD R7, R5, R6
  CMP R7, R9
  BEQ WhileLoop
  BNE End_Main



IsAssociativeMagicSquare:
  MOV R0, #3
  B   End_Main



NotAMagicSquare:
  MOV R0, #0
  B   End_Main

End_Main:
  MOV R3, #0
  MOV R4, #0
  MOV R5, #0
  MOV R6, #0
  MOV R7, #0
  MOV R8, #0
  MOV R9, #0
  MOV R10, #0
  MOV R11, #0
  MOV R12, #0

  
  BX    LR

  .end