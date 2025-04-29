/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   Brilliant Omega Outstanding Battle Simulator
 * Project Author: Benjamin Jenner
 * GitHub Repo:    https://github.com/waimea-bsjenner/level-2-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * =====================================================================
 */
const val DIVIDER = "---------------------------------------------------------------------------------------------"
const val RULES = "Brilliant Omega Outstanding Battle Simulator is a turn based 1v1 battle simulator, in which you and a friend pick a race and weapon, and duke it out.\nRaces include: the fast Elf, the sturdy Dwarf, and the average Human.\nWeapons include: Longsword, Rapier, Quickbow, Longbow, Spellbook, Woodstaff\nYou can spend your turn trying to attack the opponent, use an health potion, or move further away or closer to you opponent."
const val ERROR = "Please choose a valid option."
// (Health, Movement speed)
val DWARF = listOf(125,2)
val ELF = listOf(75,4)
val HUMAN = listOf(100,3)
// (Damage, Attack range, binary for multiple attacks)
val LONGSWORD = listOf(50,3,0)
val RAPIER = listOf(20,3,1)
val LONGBOW = listOf(40,9,0)
val QUICKBOW = listOf(16,9,1)
val WOODSTAFF = listOf(45,6,0)
val SPELLBOOK = listOf(18,6,1)
// classifying magic numbers
const val HEALTH = 0
const val SPEED = 1
const val DAMAGE = 2
const val RANGE = 3
const val MULTI = 4
const val POTION = 5

/**
 * This is the main function. Within it is:
 * The rules
 * Name get
 * The stats of both players
 * the loop for taking turns
 */
fun main() {
    println(DIVIDER)
    println("BRILLIANT OMEGA OUTSTANDING BATTLE SIMULATOR")
    println(DIVIDER)
    println("Welcome to the Brilliant Omega Outstanding Battle Simulator. Would you like to know the rules? (Y)es/(N)o")
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

    val p1Name = getString("What is the name of player 1?")
    var p2Name: String
    // Making sure players 1 and 2 can't have the same names
    while (true) {
        p2Name = getString("What is the name of player 2 (can't be the same as player 1)?")
        if (p2Name == p1Name) println(ERROR)
        else break
    }
    val p1Stats = setUpCharacterStats(p1Name)
    val p2Stats = setUpCharacterStats(p2Name)

    val playerNames = listOf(p1Name, p2Name)
    val playerStats = listOf(p1Stats, p2Stats)

    // the number of spaces between the players
    var distance = 4

    var currentPlayer = 0
    var currentOpponent = 1

    /**
     * The main loop for attacking
     */
    while (p1Stats[HEALTH] > 0 && p2Stats[HEALTH] > 0) {

        // decide who's turn it is
        val player = playerStats[currentPlayer]
        val opponent = playerStats[currentOpponent]
        println()
        println()
        println(DIVIDER)
        println("BATTLEFIELD")
        println(DIVIDER)
        println()
        print(playerNames[0])
        for (i in 1..distance) print("_")
        println(playerNames[1])
        println()
        println(DIVIDER)
        println()
        println("${playerNames[currentPlayer]}, choose an action to take.")
        println(DIVIDER)
        println("ATTACK (A)")
        println("MOVE (M)")
        println("HEAL (H)")
        println(DIVIDER)
        while (true) {
            when (readln().uppercase()) {
                // the attack option
                "A" -> {
                    if (distance <= player[RANGE]) {
                        // if the player can multi attack
                        if (player[MULTI] == 1) {
                            val attackNumber = (1..4).random()
                            println("${playerNames[currentPlayer]} attacks ${playerNames[currentOpponent]} $attackNumber times for ${attackNumber * player[DAMAGE]} damage!!".red())
                            print("${playerNames[currentOpponent]} goes from ${opponent[HEALTH]} health ")
                            for (i in 1..attackNumber) {
                                opponent[HEALTH] -= player[DAMAGE]
                            }
                            println("to ${opponent[HEALTH]} health!")
                        }
                        // if the player's name is "Kieran" because why not
                        else if (player[MULTI] == 2) {
                            println("Kieran attacks ${playerNames[currentOpponent]} [INFINITY] times for [INFINITY] damage!".red())
                            print("${playerNames[currentOpponent]} goes from ${opponent[HEALTH]} health ")
                            for (i in 1..9999999) {
                                opponent[HEALTH] -= player[DAMAGE]
                            }
                            println("to ${opponent[HEALTH]} health!")
                        }
                        // if the player cant multi attack
                        else {
                            println("${playerNames[currentPlayer]} attacks ${playerNames[currentOpponent]} for ${player[DAMAGE]}!".red())
                            print("${playerNames[currentOpponent]} goes from ${opponent[HEALTH]} health ")
                            opponent[HEALTH] -= player[DAMAGE]
                            println("to ${opponent[HEALTH]} health!")
                        }
                    }
                    // if the opponent is out of the attack range
                    else {
                        println("${playerNames[currentOpponent]} is too far away to attack! you miss!".blue())
                    }
                    break
                }
                // movement option
                "M" -> {
                    while (true) {
                        when (currentPlayer) {
                            // player 1 is on the left side so they cant move right beyond player 2
                            0 -> {
                                println("Would you like to move left or right?")
                                when (readln().uppercase()) {
                                    "L" -> distance += player[SPEED]
                                    "R" -> {
                                        if (distance < player[SPEED]) distance = 0
                                        else distance -= player[SPEED]
                                    }
                                }
                            }
                            // player 2 is on the right side so they cant move left beyond player 1
                            1 -> {
                                println("Would you like to move left or right?")
                                when (readln().uppercase()) {
                                    "R" -> distance += player[SPEED]
                                    "L" -> {
                                        if (distance < player[SPEED]) distance = 0
                                        else distance -= player[SPEED]
                                    }
                                }
                            }
                            else -> println(ERROR)
                        }
                        break
                    }
                    break
                }
                // healing
                "H" -> {
                    if (player[POTION] > 0) {
                        println("${playerNames[currentPlayer]} drinks a health potion!".green())
                        print("Their health goes from ${player[HEALTH]} ".green())
                        player[HEALTH] += (40..50).random()
                        println("to ${player[HEALTH]}!".green())
                        player[POTION] -= 1

                    } else {
                        println("${playerNames[currentPlayer]} reaches for a health potion, but there's nothing there!")
                    }
                    break
                }
                else -> println(ERROR)
            }
        }
        // swaps turns
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
    if (p1Stats[HEALTH] < 0) println("$p1Name is out of health! $p2Name wins!")
    else println("$p2Name is out of health! $p1Name wins!")
}

/**
 * this function gets the player to input a race for their character using the getChar function
 */
fun chooseRace(prompt: String): String {
    println(DIVIDER)
    println("Dwarf: 125 health, move speed of 2. (D)")
    println("Elf: 75 health, move speed of 4. (E)")
    println("Human: 100 health, move speed of 3. (H)")
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

/**
 * this function gets the player to input a weapon for their character also using the getChar function
 */
fun chooseWeapon(prompt: String): String {
    println(DIVIDER)
    println("Longsword: range of 3, 1 attack, 50 damage. (S)")
    println("Rapier: range of 3, 1-4 attacks, 20 damage. (R)")
    println("Longbow: range of 9, 1 attack, 40 damage. (L)")
    println("Quickbow: range of 9, 1-4 attacks, 16 damage. (Q)")
    println("Woodstaff: range of 6, 1 attack, 45 damage. (W)")
    println("Spellbook: range of 6, 1-4 attacks, 18 damage. (B)")
    println(DIVIDER)
    while (true) {
        val char = getChar(prompt).uppercaseChar()
        when (char) {
            'S' -> return "Longsword"
            'R' -> return "Rapier"
            'L' -> return "Longbow"
            'Q' -> return "Quickbow"
            'W' -> return "Woodstaff"
            'B' -> return "Spellbook"
            else -> println(ERROR)
        }
    }
}

/**
 * this function is what get all the character stats and puts it into a neat little list
 */
fun setUpCharacterStats(name: String): MutableList<Int> {
    val stats = mutableListOf<Int>()
    //funny kieran meme
    if (name == "Kieran") {
        println("Kieran detected")
        println("Kieran has been automatically sorted into the deity race")
        println("Adding unlimited damage...")
        println("Adding unlimited health...")
        println("Adding unlimited speed...")
        println("Adding unlimited range...")
        println("Adding million-attack attribute...")
        println("Adding unlimited potions...")
        println("Success!")
        stats.add(9999999)
        stats.add(9999999)
        stats.add(9999999)
        stats.add(9999999)
        stats.add(2)
        stats.add(9999997)
    }
    // this uses the chooseRace and chooseWeapon functions to build the character
    else {
        val race = chooseRace("$name, choose a race of character")
        println("$name has picked $race")
        val weapon = chooseWeapon("$name, choose a weapon")
        println("$name has picked $weapon")
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
    }
    // this adds two health potions
    stats.add(2)
    return stats
}

/**
 * This is a simple getString function, which gets a string from the user
 */
fun getString(prompt: String): String {
    var userInput: String

    while (true) {
        println(prompt)

        userInput = readln()
        if (userInput.isNotBlank()) break
    }
    return userInput
}

/**
 * This is a function which uses the getString function, then takes the first character of it and returns that
 */
fun getChar(prompt: String): Char {

    val userInput: Char = getString(prompt)[0]
    return userInput
}