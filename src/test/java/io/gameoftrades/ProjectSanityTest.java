package io.gameoftrades;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Controleert of de studentNN package is hernoemd naar de variant met het groep nummer
 * en dat HandelaarImpl beschikbaar is in die package.
 */
public class ProjectSanityTest {

    @Test()
    public void heeftStudentDefaultAangepast() {
        String base = "io.gameoftrades.studentNN.HandelaarImpl";
        try {
            Class.forName(base);
            fail(base + " is not niet hernoemd.");
        } catch (ClassNotFoundException ex) {
            // Good!
        }
    }

    @Test
    public void heeftStudentPackageGroepNummerGegeven() {
        String base = "io.gameoftrades.student";
        Class<?> found = null;
        try {
            for (int t = 0; t < 100; t++) {
                String nr = (t < 10 ? "0" : "") + String.valueOf(t);
                try {
                    Class<?> tmp = Class.forName(base + nr + ".HandelaarImpl");
                    if (tmp != null) {
                        if (found == null) {
                            found = tmp;
                        } else {
                            fail("Meerdere packages gevonden '" + tmp.getName() + "' en '" + found.getName() + "'");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    // Ignore
                }
            }
            assertTrue("HandelaarImpl in package met Student Groep nummer niet gevonden", found != null);
            found.newInstance();
        } catch (InstantiationException e) {
            fail("HandelaarImpl kon niet geinstantieerd worden met een no-args constructor.");
        } catch (IllegalAccessException e) {
            fail("de no-args constructor van HandelaarImpl is niet public.");
        }
    }

    @Test
    public void heeftPomNieuwArtifactIdGegeven() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("./pom.xml")))) {
            List<String> aid = reader.lines().filter(s -> s.contains("<artifactId>")).collect(Collectors.toList());
            for (String s : aid) {
                assertFalse(s.contains("gameoftrades-student-kit"));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
