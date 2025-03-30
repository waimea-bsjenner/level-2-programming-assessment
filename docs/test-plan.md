# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## chooseRace function

I have made a function to get the player to choose a race for their character. 

### Test Data To Use

Details of test data and reasons for selection. Details of test data and reasons for selection. Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. 

---

## getChar function

It should get a character from the user.

### Test Data To Use

Details of test data and reasons for selection. Details of test data and reasons for selection. Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. Statement detailing what should happen. 

---

## Multiple attack weapon testing

should check the 5th index to see if a weapon attacks multiple times, then attack 1-4 times (random)

### Test data to use

I picked a multi-attack weapon and tried to use it, but I got an out-of-bounds error
![multiAttackTest]()

### expected result

It should've hopefully picked a random number from 1-4 and took away the enemy's health that many times

### fix

I fixed the range to index format, starting at 0 and ending