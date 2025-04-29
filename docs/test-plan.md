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

I will select each character for the race, and try some invalid inputs

### Expected Test Result

it should reject invalid inputs from the user and ask for them to try again, and it should accept valid characters (uppercase and lowercase) and apply the appropriate race 

---

## getChar function

It should get a character from the user.

### Test Data To Use

I'll enter some strings and characters and integers

### Expected Test Result

it should return the first character in a string given by the user. 

---

## Multiple attack weapon testing

makes some weapons attack the opponent multiple times

### Test data to use

Will try using a multi attacking weapon

### expected result

It should hopefully pick a random number from 1-4 and attack the opponent that many times

---

## Health potion system

gives the players a way to heal during battle

### test data to use

I'll use a health potion and get it to print the number of health and potions before and after.

### expected result 

It should check if the player has any health potions available, if so, it should add a random number between 40 and 50 to the players health, and take away one of their potions. If the player doesn't have a health potion, it wastes their turn because im evil.

---

## End game if one player's health is less than 0

gives a way to conclude a winner

### test data to use

put the turn system in while loop that continues if both player's HP is above 0

### expected result

should announce a winner and end the loop and process if a player's health goes to or below 0

---

## Turn system and end loop works

see if the game actually works and is playable

### test data to use

I'll play a test match

### expected result

It shouldn't break and use appropriate values