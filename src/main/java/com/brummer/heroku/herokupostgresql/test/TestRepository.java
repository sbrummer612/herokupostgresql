package com.brummer.heroku.herokupostgresql.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long>{

}
