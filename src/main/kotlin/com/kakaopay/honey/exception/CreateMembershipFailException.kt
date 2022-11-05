package com.kakaopay.honey.exception

class CreateMembershipFailException(
    override val message: String = MESSAGE
) : RuntimeException() {
    companion object {
        private const val MESSAGE = "멤버십코드 생성을 실패하였습니다."
    }
}
