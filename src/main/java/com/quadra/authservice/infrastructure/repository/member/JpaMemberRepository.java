package com.quadra.authservice.infrastructure.repository.member;

import com.quadra.authservice.infrastructure.repository.member.schema.MemberSchema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberSchema, Long> {
}
