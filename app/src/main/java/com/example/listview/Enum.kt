package com.example.listview

enum class Direction(val degrees: Int) {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    fun next(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

enum class Planet(val mass: Double, val radius: Double) {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6);

    // Universal gravitational constant  (m^3 kg^-1 s^-2)
    companion object {
        private const val G = 6.67300e-11
    }

    fun surfaceGravity(): Double {
        return G * mass / (radius * radius)
    }

    fun surfaceWeight(otherMass: Double): Double {
        return otherMass * surfaceGravity()
    }
}
enum class Operation {
    ADD {
        override fun apply(a: Int, b: Int) = a + b
    },
    SUBTRACT {
        override fun apply(a: Int, b: Int) = a - b
    },
    MULTIPLY {
        override fun apply(a: Int, b: Int) = a * b
    },
    DIVIDE {
        override fun apply(a: Int, b: Int) = a / b
    };
    abstract fun apply(a: Int, b: Int): Int
}
interface Printable {
    fun print()
}

enum class Color : Printable {
    RED {
        override fun print() {
            println("Color is Red")
        }
    },
    GREEN {
        override fun print() {
            println("Color is Green")
        }
    },
    BLUE {
        override fun print() {
            println("Color is Blue")
        }
    }
}
fun main() {
    val result = Operation.ADD.apply(3, 4)
    println(result)  // Output: 7
    val direction = Direction.NORTH
    println(direction.degrees)  // Output: 0
    println(direction.next())   // Output: EAST
    val earthWeight = 100.0
    val mass = earthWeight / Planet.EARTH.surfaceGravity()

    for (p in Planet.entries) {
        println("Your weight on ${p.name} is ${p.surfaceWeight(mass)}")
    }
    Color.RED.print()   // Output: Color is Red
    Color.GREEN.print() // Output: Color is Green
    Color.BLUE.print()  // Output: Color is Blue
}
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val exception: Throwable) : Result()
    object Loading : Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Result.Success -> println("Data: ${result.data}")
        is Result.Error -> println("Error: ${result.exception.message}")
        Result.Loading -> println("Loading...")
    }
}
    //sealed class у Kotlin використовується для обмеження набору можливих підкласів. Це дозволяє мати фіксовану кількість підкласів, що можуть бути визначені всередині одного файлу або бути членами sealed класу. Це особливо корисно для створення чітких і контрольованих ієрархій класів, наприклад, для обробки станів або подій.
    //
    //Суть sealed class
    //Контрольованість: Усі підкласи sealed класу повинні бути визначені у тому ж файлі, де й сам sealed клас. Це дозволяє програмісту знати всі можливі типи, які може представляти sealed клас.
    //Безпечна перевірка типів: Завдяки sealed class, компілятор знає всі можливі підкласи, і в конструкціях when може вимагати, щоб усі варіанти були оброблені, що зменшує кількість помилок у коді.

sealed class Shape(val name: String) {
    // Клас може мати методи
    abstract fun area(): Double

    // Підкласи успадковують конструктор Shape
    data class Circle(val radius: Double) : Shape("Circle") {
        override fun area(): Double = Math.PI * radius * radius
    }

    data class Rectangle(val width: Double, val height: Double) : Shape("Rectangle") {
        override fun area(): Double = width * height
    }

    // Об'єкт може представляти фіксований стан або тип
    object Unknown : Shape("Unknown") {
        override fun area(): Double = 0.0
    }
}

fun describeShape(shape: Shape) {
    when (shape) {
        is Shape.Circle -> println("A ${shape.name} with area: ${shape.area()}")
        is Shape.Rectangle -> println("A ${shape.name} with area: ${shape.area()}")
        Shape.Unknown -> println("Unknown shape with no area")
    }
}

fun main2() {
    val circle = Shape.Circle(5.0)
    val rectangle = Shape.Rectangle(4.0, 6.0)
    val unknown = Shape.Unknown

    describeShape(circle)       // Output: A Circle with area: 78.53981633974483
    describeShape(rectangle)    // Output: A Rectangle with area: 24.0
    describeShape(unknown)      // Output: Unknown shape with no area
}