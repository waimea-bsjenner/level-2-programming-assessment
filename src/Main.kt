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

fun main() {
    println(DIVIDER)
    println("BRILLIANT OMEGA OUTSTANDING BATTLE SIMULATOR")
    val sayRules = getChar("Welcome to the Brilliant Omega Outstanding Battle Simulator, or BOOBS for short. Would you like to know the rules? (y/n)")
    sayRules.uppercase()
    val p1Race = chooseRace("Player 1, choose a race of character")
    println(p1Race)
}

fun chooseRace(prompt: String): Char {
    println("Dwarf: strong, sturdy, tough, but not the lightest on feet. (D)")
    println("Elf: elegant, agile, quick, but rather squishy. (E)")
    println("Human: the jack of all trades, master of none. Exceptionally average. (H)")
    while (true) {
        when (val race = getChar("")) {
            'D' -> return race
            'E' -> return race
            'H' -> return race
            else -> println("Please pick a valid option, D, E, or H.")
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
    var userInput: String

    while (true) {
        print(prompt)

        userInput = readln()
        if (userInput.isNotBlank()) break
    }
    return userInput.first()
}

fun rules() {
    println("BOOBS is a turn based 1v1 battle simulator, in which you and a friend pick a race, class, weapon, and duke it out.")
    println("Races include: the fast Elf, the sturdy Dwarf, and the average Human")
    println("Classes include: melee, ranger, mage")
    println("Weapons include: longsword, rapier, shortbow, longbow, spellbook, magic staff")
    println("You can spend your turn trying to attack the opponent, use an item, or move further away or closer to you opponent")
    println("")
}