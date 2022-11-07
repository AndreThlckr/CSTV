package io.github.andrethlckr.cstv.match.domain

import io.github.andrethlckr.cstv.core.domain.ImageUrl
import io.kotest.matchers.shouldBe
import org.junit.Test

class PlayerTest {

    @Test
    fun `fullName should be first and last name combined`() {
        val player = playerWith(
            firstName = "Inigo",
            lastName = "Montoya"
        )

        player.fullName shouldBe "Inigo Montoya"
    }

    companion object {

        fun playerWith(
            id: PlayerId = PlayerId(1),
            nickname: String = "FalleN",
            firstName: String = "Gabriel",
            lastName: String = "Toledo",
            image: ImageUrl? = null,
        ) = Player(
            id = id,
            nickname = nickname,
            firstName = firstName,
            lastName = lastName,
            image = image
        )
    }
}
