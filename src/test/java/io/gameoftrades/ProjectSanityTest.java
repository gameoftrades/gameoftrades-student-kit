package io.gameoftrades;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

}
