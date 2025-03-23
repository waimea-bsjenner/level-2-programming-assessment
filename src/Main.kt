/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   Brilliant Omega Outstanding Battle Simulator (B.O.O.B.S)
 * Project Author: Benjamin Jenner
 * GitHub Repo:    https://github.com/waimea-bsjenner/level-2-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * races to add minimum: Dwarf - sturdy but less fast, Human - goldilocks, Elf - quick but flimsy
 * classes to add minimum: handheld weapon user, ranged weapon user, magic user
 * weapons to add minimum: longsword - slow and heavy hitting, rapier - light and quick,
 *                         longbow - high power arrows, shortbow - lots of small arrows,
 *                         spellbook - quick cast light spells, staff - slow cast strong spells
 *                         spells: magic missile - quick, low damage, fireball - slow big damage
 * movement stuff: system to have distance between 2 players - attacks have certain range
 *                 walk/run to move
 *                 dash moves you further but makes you inactive for the next turn
 *
 * =====================================================================
 */
const val DIVIDER = "---------------------------------------------------------------------------------------------"
const val RULES = "BOOBS is a turn based 1v1 battle simulator, in which you and a friend pick a race, class, weapon, and duke it out.\nRaces include: the fast Elf, the sturdy Dwarf, and the average Human.\nClasses include: melee - close range high dps, ranger - long range mid dps, mage - has something for every situation.\nWeapons include: longsword, rapier, shortbow, longbow, spellbook, magic staff\nYou can spend your turn trying to attack the opponent, use an item, or move further away or closer to you opponent."
const val ERROR = "Please choose a valid option."

fun main() {
    println(DIVIDER)
    println("BRILLIANT OMEGA OUTSTANDING BATTLE SIMULATOR")
    println(DIVIDER)
    var sayRules = getChar("Welcome to the Brilliant Omega Outstanding Battle Simulator, or BOOBS for short. Would you like to know the rules? (y/n) ").uppercase()

    val player1 = getString("What is the name of Player 1? ")
    val player2 = getString("What is the name of Player 2? ")
    val p1Race = chooseRace("Player 1, choose a race of character")
    println("$player1 has picked $p1Race")
    val p2Race = chooseRace("Player 2, choose a race of character")
    println("$player2 has picked $p2Race")
    val p1Class = chooseClass("Player 1, choose a class")
    println("$player1 has picked $p1Class")
    val p2Class = chooseClass("Player 2, choose a class")
    println("$player2 has picked $p2Class")
}

fun chooseRace(prompt: String): String {
    println("Dwarf: strong, sturdy, tough, but not the lightest on feet. (D)")
    println("Elf: elegant, agile, quick, but rather squishy. (E)")
    println("Human: the jack of all trades, master of none. Exceptionally average. (H)")
    while (true) {
        when (getChar(prompt)) {
            'D' -> return "Dwarf"
            'E' -> return "Elf"
            'H' -> return "Human"
            else -> println(ERROR)
        }
    }
}

fun chooseClass(prompt: String): String {
    println("Melee: close range, with metal sticks that output a lot of damage. (S)")
    println("Ranged: long range, using sticks and string to lob projectiles. (R)")
    println("Mage: medium range, with sticks and books to cast spells and shiz. (M)")
    while (true) {
        when (getChar(prompt)) {
            'M' -> return "Mage"
            'R' -> return "Ranged"
            'S' -> return "Melee"
            else -> println(ERROR)
        }
    }
}

fun getString(prompt: String): String {
    var userInput: String

    while (true) {
        print(prompt)

        userInput = readln()
        if (userInput.isNotBlank()) break
    }
    return userInput
}

fun getChar(prompt: String): Char {
    val userInput: Char

    while (true) {
        print(prompt)

        userInput = getString(prompt)[0]
        break
    }
    return userInput
}
