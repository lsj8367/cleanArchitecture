package io.github.lsj8367;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.github.lsj8367.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Test;

class DependencyRuleTests {

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext("io.github.lsj8367.account")

            .withDomainLayer("domain")

            .withAdaptersLayer("adapter")
            .incoming("in.web")
            .outgoing("out.persistence")
            .and()

            .withApplicationLayer("application")
            .services("service")
            .incomingPorts("port.in")
            .outgoingPorts("port.out")
            .and()

            .withConfiguration("configuration")
            .check(new ClassFileImporter()
                .importPackages("io.github.lsj8367.."));
    }

    @Test
    void testPackageDependencies() {
        noClasses()
            .that()
            .resideInAPackage("io.github.lsj8367.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("io.github.lsj8367.application..")
            .check(new ClassFileImporter()
                .importPackages("io.github.lsj8367.."));
    }

}
