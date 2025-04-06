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
 * movement stuff: system to have distance between 2 players - attacks have certain range
 *                 walk/run to move
 *                 dash moves you further but makes you inactive for the next turn
 *
 * =====================================================================
 */
const val DIVIDER = "---------------------------------------------------------------------------------------------"
const val RULES = "BOOBS is a turn based 1v1 battle simulator, in which you and a friend pick a race and weapon, and duke it out.\nRaces include: the fast Elf, the sturdy Dwarf, and the average Human.\nWeapons include: longsword, rapier, quickbow, longbow, spellbook, magic staff\nYou can spend your turn trying to attack the opponent, use an item, or move further away or closer to you opponent."
const val ERROR = "Please choose a valid option."
// (Health, Movement speed)
val DWARF = listOf<Int>(125,2)
val ELF = listOf<Int>(75,4)
val HUMAN = listOf<Int>(100,3)
// (Damage, Attack range, binary for multiple attacks)
val LONGSWORD = listOf<Int>(50,3,0)
val RAPIER = listOf<Int>(20,3,1)
val LONGBOW = listOf<Int>(40,9,0)
val QUICKBOW = listOf<Int>(16,9,1)
val WOODSTAFF = listOf<Int>(45,6,0)
val SPELLBOOK = listOf<Int>(18,6,1)
const val PLAYERNAME = 0
const val HEALTH = 0
const val SPEED = 1
const val DAMAGE = 2
const val RANGE = 3
const val MULTI = 4
const val POTION = 5

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

    val p1Name = getPlayerName()
    val p2Name = getPlayerName()

    val p1Stats = setUpCharacterStats(p1Name)
    val p2Stats = setUpCharacterStats(p2Name)

    val playerNames = listOf(p1Name, p2Name)
    val playerStats = listOf(p1Stats, p2Stats)

    // (health, movespeed, damage, range, multi attack, health potion number)
    // the loop for attacking
    var distance = 4

    var currentPlayer = 0
    var currentOpponent = 1

    while (p1Stats[HEALTH] > 0 && p2Stats[HEALTH] > 0) {

        val player = playerStats[currentPlayer]
        val opponent = playerStats[currentOpponent]

        println()
        println("NEXT TURN!")
        println(DIVIDER)
        println("BATTLEFIELD")
        println(DIVIDER)
        print(playerNames[0])
        for (i in 1..distance) print("_")
        println(playerNames[1])
        println(DIVIDER)
        println("${playerNames[currentPlayer]}, choose an action to take.")
        println(DIVIDER)
        println("ATTACK (A)")
        println("MOVE (M)")
        println("DASH (D)")
        println("HEAL (H)")
        println(DIVIDER)
        when (readln().uppercase()) {
            "A" -> {
                if (distance <= player[RANGE]) {
                    if (player[MULTI] == 1) {
                        val attackNumber = (1..4).random()
                        println("${playerNames[currentPlayer]} attacks ${playerNames[currentOpponent]} $attackNumber times!".red())
                        for (i in 1..attackNumber) {
                            opponent[HEALTH] -= player[DAMAGE]
                        }
                    }
                    else {
                        println("${playerNames[currentPlayer]} attacks ${opponent[PLAYERNAME]}!".red())
                        opponent[HEALTH] -= player[DAMAGE]
                    }
                }
                else {
                    println("${opponent[PLAYERNAME]} is too far away to attack! you miss!".blue())
                }
            }

            "M" -> {
                println("Would you like to move left or right? (L/R)")
                when (readln().uppercase()) {
                    "L" -> distance += player[SPEED]
                    "R" -> {
                        if (distance < player[SPEED]) distance = 0
                        else distance -= player[SPEED]
                    }
                }
            }

            "H" -> {
                if (player[POTION] > 0) {
                    println("${player[PLAYERNAME]} drinks a health potion!".green())
                    player[HEALTH] += (40..50).random()
                    player[POTION] -= 1
                }
                else {
                    println("${player[PLAYERNAME]} reaches for a health potion, but there's nothing there!")
                }
            }

            "D" -> {
                println("Would you like to dash left or right? (L/R)")
                when (currentPlayer) {
                    0 -> {
                        when (readln().uppercase()) {
                            "L" -> distance += (2 * player[SPEED])
                            "R" -> {
                                if (distance < player[SPEED]) distance = 0
                                else distance -= (2 * player[SPEED])
                            }
                        }
                    }

                    1 -> {
                        when (readln().uppercase()) {
                            "R" -> distance += (2 * player[SPEED])
                            "L" -> {
                                if (distance < player[SPEED]) distance = 0
                                else distance -= (2 * player[SPEED])
                            }
                        }
                    }
                }

            }
        }
        when (currentPlayer) {
            1 -> {
                currentPlayer = 0
                currentOpponent = 1
            }
            0 -> {
                currentOpponent = 0
                currentPlayer = 1
            }
        }
    }
}

fun chooseRace(prompt: String): String {
    println(DIVIDER)
    println("Dwarf: strong, sturdy, tough, but not the lightest on feet. (D)")
    println("Elf: elegant, agile, quick, but rather squishy. (E)")
    println("Human: the jack of all trades, master of none. Exceptionally average. (H)")
    println(DIVIDER)
    while (true) {
        val char = getChar(prompt).uppercaseChar()
        when (char) {
            'D' -> return "Dwarf"
            'E' -> return "Elf"
            'H' -> return "Human"
            else -> println(ERROR)
        }
    }
}

fun chooseWeapon(prompt: String): String {
    println(DIVIDER)
    println("Longsword: close range, slow, heavy attacks. (S)")
    println("Rapier: close range, quick, light attacks. (R)")
    println("Longbow: long range, slow, heavy attacks. (L)")
    println("Quickbow: long range, quick, light attacks. (Q)")
    println("Woodstaff: medium range, slow, heavy attacks. (W)")
    println("Spellbook: medium range, quick, light attacks. (B)")
    println(DIVIDER)
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

fun getPlayerName(): String {
    return getString("What is the name of player 2?")
}

fun setUpCharacterStats(name: String): MutableList<Int> {
    val stats = mutableListOf<Int>()

    val race = chooseRace("${name}, choose a race of character")
    println("${name} has picked $race")
    val weapon = chooseWeapon("${name}, choose a weapon")
    println("${name} has picked $weapon")
    when (race) {
        "Dwarf" -> stats.add(DWARF[0]) && stats.add(DWARF[1])
        "Elf" -> stats.add(ELF[0]) && stats.add(ELF[1])
        "Human" -> stats.add(HUMAN[0]) && stats.add(HUMAN[1])
    }
    when (weapon) {
        "Longsword" -> stats.add(LONGSWORD[0]) && stats.add(LONGSWORD[1]) && stats.add(LONGSWORD[2])
        "Rapier" -> stats.add(RAPIER[0]) && stats.add(RAPIER[1]) && stats.add(RAPIER[2])
        "Longbow" -> stats.add(LONGBOW[0]) && stats.add(LONGBOW[1]) && stats.add(LONGBOW[2])
        "Quickbow" -> stats.add(QUICKBOW[0]) && stats.add(QUICKBOW[1]) && stats.add(QUICKBOW[2])
        "Woodstaff" -> stats.add(WOODSTAFF[0]) && stats.add(WOODSTAFF[1]) && stats.add(WOODSTAFF[2])
        "Spellbook" -> stats.add(SPELLBOOK[0]) && stats.add(SPELLBOOK[1]) && stats.add(SPELLBOOK[2])
    }

    stats.add(2)
    return stats
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