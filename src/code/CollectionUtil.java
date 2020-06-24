package code;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * CollectionUtil bietet Methoden zur Umwandlung und Behandlung von
 * Collection-Typen an.
 */
@SuppressWarnings("SpellCheckingInspection")
public class CollectionUtil {

    /**
     * Entfernt doppelte Einträge aus der übergebenen Liste.
     *
     * @param list eine Liste mit doppelten Einträgen
     * @return die um doppelte Einträge bereinigte Liste
     */
    public static ArrayList<Datensatz> removeDuplicate(ArrayList<Datensatz> list) {
        Set<Datensatz> set = transformListIntoSet(list);
        return transformSetIntoList(set);
    }

    /**
     * Wandelt eine List in ein Set um. Die Reihenfolge der Objekte
     * in der Liste bleibt dabei auch im Set erhalten.
     *
     * @param list die Liste, die in ein Set verwandelt werden soll
     * @return der Ergebnis-Typ, ein Set
     */
    public static Set<Datensatz> transformListIntoSet(ArrayList<Datensatz> list) {
        Set<Datensatz> set = new LinkedHashSet<>(list);
        return set;
    }

    /**
     * Wandelt ein Set in eine List um.
     *
     * @param set das Set, das in eine List verwandelt werden soll
     * @return der Ergebnis-Typ, eine List
     */
    public static ArrayList<Datensatz> transformSetIntoList(Set<Datensatz> set) {
        return new ArrayList<>(set);
    }
}
