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
const val RULES = "BOOBS is a turn based 1v1 battle simulator, in which you and a friend pick a race and weapon, and duke it out.\nRaces include: the fast Elf, the sturdy Dwarf, and the average Human.y\nWeapons include: longsword, rapier, quickbow, longbow, spellbook, magic staff\nYou can spend your turn trying to attack the opponent, use an item, or move further away or closer to you opponent."
const val ERROR = "Please choose a valid option."
// (Health, Movement speed)
val DWARF = listOf<Int>(125,2)
val ELF = listOf<Int>(75,4)
val HUMAN = listOf<Int>(100,3)
// (Damage, Attack range)
val LONGSWORD = listOf<Int>(50,3)
val RAPIER = listOf<Int>(20,3)
val LONGBOW = listOf<Int>(40,9)
val QUICKBOW = listOf<Int>(16,9)
val WOODSTAFF = listOf<Int>(45,6)
val SPELLBOOK = listOf<Int>(18,6)
fun main() {
    println(DIVIDER)
    println("BRILLIANT OMEGA OUTSTANDING BATTLE SIMULATOR")
    println(DIVIDER)
    println("Welcome to the Brilliant Omega Outstanding Battle Simulator, or BOOBS for short. Would you like to know the rules? (y/n) ")
    while (true) {
        val printRules = readln().lowercase()
        if (printRules == "y") {
            println(RULES)
            break
        }
        else if (printRules == "n") {
            println("Have fun then!")
            break
        }
        else println(ERROR)
    }
    var p1 = setUpCharacter1Stats()
    var p2 = setUpCharacter2Stats()
    println(p1)
    println(p2)
}

fun chooseRace(prompt: String): String {
    println("Dwarf: strong, sturdy, tough, but not the lightest on feet. (D)")
    println("Elf: elegant, agile, quick, but rather squishy. (E)")
    println("Human: the jack of all trades, master of none. Exceptionally average. (H)")
    while (true) {
        val char = getChar(prompt).uppercaseChar()
        if (char == 'D') return "Dwarf"
        else if (char == 'E') return "Elf"
        else if (char == 'H') return "Human"
        else println(ERROR)
    }
}

fun chooseWeapon(prompt: String): String {
    println("Longsword: close range, slow, heavy attacks. (S)")
    println("Rapier: close range, quick, light attacks. (R)")
    println("Longbow: long range, slow, heavy attacks. (L)")
    println("Quickbow: long range, quick, light attacks. (Q)")
    println("Woodstaff: medium range, slow, heavy attacks. (W)")
    println("Spellbook: medium range, quick, light attacks. (B)")
    while (true) {
        val char = getChar(prompt).uppercaseChar()
        if (char == 'S') return "Longsword"
        else if (char == 'R') return "Rapier"
        else if (char == 'L') return "Longbow"
        else if (char == 'Q') return "Quickbow"
        else if (char == 'W') return "Woodstaff"
        else if (char == 'B') return "Spellbook"
        else println(ERROR)
    }
}
fun setUpCharacter2Stats(): MutableList<Int> {
    val p2 = mutableListOf<Int>()
    val player2 = getString("What is the name of Player 2? ")
    val p2Race = chooseRace("Player 2, choose a race of character")
    println("$player2 has picked $p2Race")
    val p2Weapon = chooseWeapon("Player 2, choose a weapon")
    println("$player2 has picked $p2Weapon")
    when (p2Race) {
        "Dwarf" -> p2.add(DWARF[0]) && p2.add(DWARF[1])
        "Elf" -> p2.add(ELF[0]) && p2.add(ELF[1])
        "Human" -> p2.add(HUMAN[0]) && p2.add(HUMAN[1])
    }
    when (p2Weapon) {
        "Longsword" -> p2.add(LONGSWORD[0]) && p2.add(LONGSWORD[1])
        "Rapier" -> p2.add(RAPIER[0]) && p2.add(RAPIER[1])
        "Longbow" -> p2.add(LONGBOW[0]) && p2.add(LONGBOW[1])
        "Quickbow" -> p2.add(QUICKBOW[0]) && p2.add(QUICKBOW[1])
        "Woodstaff" -> p2.add(WOODSTAFF[0]) && p2.add(WOODSTAFF[1])
        "Spellbook" -> p2.add(SPELLBOOK[0]) && p2.add(SPELLBOOK[1])
    }
    return p2
}
fun setUpCharacter1Stats(): MutableList<Int> {
    val p1 = mutableListOf<Int>()
    val player1 = getString("What is the name of Player 1? ")
    // ask p1 for their character's race
    val p1Race = chooseRace("Player 1, choose a race of character")
    println("$player1 has picked $p1Race")
    // ask p1 for their character's weapon
    val p1Weapon = chooseWeapon("Player 1, choose a weapon")
    println("$player1 has picked $p1Weapon")
    // give appropriate stats in the list form of (health, movement speed, damage, attack range)
    when (p1Race) {
        "Dwarf" -> p1.add(DWARF[0]) && p1.add(DWARF[1])
        "Elf" -> p1.add(ELF[0]) && p1.add(ELF[1])
        "Human" -> p1.add(HUMAN[0]) && p1.add(HUMAN[1])
    }

    when (p1Weapon) {
        "Longsword" -> p1.add(LONGSWORD[0]) && p1.add(LONGSWORD[1])
        "Rapier" -> p1.add(RAPIER[0]) && p1.add(RAPIER[1])
        "Longbow" -> p1.add(LONGBOW[0]) && p1.add(LONGBOW[1])
        "Quickbow" -> p1.add(QUICKBOW[0]) && p1.add(QUICKBOW[1])
        "Woodstaff" -> p1.add(WOODSTAFF[0]) && p1.add(WOODSTAFF[1])
        "Spellbook" -> p1.add(SPELLBOOK[0]) && p1.add(SPELLBOOK[1])
    }
    return p1
}

fun getString(prompt: String): String {
    var userInput: String

    while (true) {
        println(prompt)

        userInput = readln()
        if (userInput.isNotBlank()) break
    }
    return userInput
}

fun getChar(prompt: String): Char {

    val userInput: Char = getString(prompt)[0]
    return userInput
}
