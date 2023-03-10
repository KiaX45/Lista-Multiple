import javax.swing.JOptionPane;

public class ListaMultiple {

    private Nodo pri, ult;

    public ListaMultiple() {
        pri = null;
        ult = null;
    }

    public void insertar() {
        Nodo nue;
        nue = new Nodo();
        int informacion;
        informacion = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL ITEM A AGREGAR"));
        nue.info = informacion;
        nue.next = null;

        if (pri == null) {
            pri = nue;
            ult = pri;
        } else {
            ult.next = nue;
            ult = nue;
        }
        this.ult.next = null;
        this.ult.family = null;
        ordenarColaPrincipal();
    }

    public void ordenarColaPrincipal() {
        if (pri == null) {
            return;
        }
        boolean intercambio;
        do {
            intercambio = false;
            Nodo anterior = null;
            Nodo actual = pri;
            Nodo siguiente = pri.next;
            while (siguiente != null) {
                if (actual.info == siguiente.info) {
                    System.out.println("No se puede ingresar el mismo valor");
                    delete(actual.info);
                    return;
                }
                if (actual.info > siguiente.info) {

                    intercambio = true;
                    if (anterior != null) {
                        anterior.next = siguiente;
                    } else {
                        pri = siguiente;
                    }

                    actual.next = siguiente.next;

                    siguiente.next = actual;

                    anterior = siguiente;
                    siguiente = actual.next;
                } else {
                    anterior = actual;
                    actual = siguiente;
                    siguiente = siguiente.next;
                }
            }
            ult = actual;
        } while (intercambio);
    }

    public void ordenarPilaFamilia(Nodo location) {
        if (pri == null) {
            return;
        }
        boolean intercambio;
        do {
            intercambio = false;
            NodoFamilia anterior = null;
            NodoFamilia actual = location.family;
            NodoFamilia siguiente = location.family.next;
            while (siguiente != null) {
                if (actual.info == siguiente.info) {
                    System.out.println("No se puede ingresar el mismo valor");
                    delete(actual.info);
                    return;
                }
                if (actual.info > siguiente.info) {

                    intercambio = true;
                    if (anterior != null) {
                        actual.next = siguiente.next;
                        siguiente.next = actual;
                        anterior = siguiente;
                        siguiente = actual.next;
                    } else {
                        actual.next = siguiente.next;
                        siguiente.next = actual;
                        location.family = siguiente;
                        anterior = siguiente;
                        siguiente = actual.next;

                    }

                } else {
                    anterior = actual;
                    System.out.println("Segundo" + siguiente.next);
                    actual = siguiente;
                    siguiente = siguiente.next;
                }
            }

        } while (intercambio);
    }

    public boolean insertFamily() {
        int info;
        Nodo location;
        info = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL NODO PRINCIPAL AL QUE QUIERE AGREGAR"));
        location = Locate(info);
        if (location == null) {
            System.out.println("No se encontro el nodo");
            return false;
        }
        info = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL ITEM A AGREGAR"));
        if (LocateFamily(info) != null) {
            System.out.println("No se puede ingresar el mismo valor");
            return false;
        }
        NodoFamilia nue = new NodoFamilia();
        nue.info = info;
        nue.next = null;
        if (location.family == null) {
            location.family = nue;
            ordenarPilaFamilia(location);
            return true;
        }

        nue.next = location.family;
        location.family = nue;
        // System.out.println(nue.next.next);
        ordenarPilaFamilia(location);
        return true;
    }

    public Nodo Locate(int info) {
        Nodo aux = pri;
        while (aux != null && aux.info != info) {
            aux = aux.next;
        }
        if (aux == null) {
            return null;
        } else {
            return aux;
        }

    }

    public Nodo LocateFamily(int info) {
        Nodo aux = pri;
        NodoFamilia auxFamilia = null;

        while (aux != null) {
            auxFamilia = aux.family;
            while (auxFamilia != null) {
                if (auxFamilia.info == info) {
                    return aux;
                }
                auxFamilia = auxFamilia.next;
            }

            aux = aux.next;
        }
        return null;

    }

    public boolean printMain() {
        System.out.println("COLA PRINCIPAL");
        if (pri == null) {
            System.out.println("COLA PRINCIPAL VACIA");
            return false;

        }
        String main = "";
        Nodo aux = pri;
        while (aux != null) {
            main += aux.info + " ";
            aux = aux.next;
        }
        main += " ->  null";

        ordenarColaPrincipal();
        System.out.println(main);
        return true;

    }

    public boolean printFamily() {
        System.out.println("PILA FAMILIA");
        if (pri == null) {
            System.out.println("COLA PRINCIPAL VACIA");
            return false;
        }
        int info;
        Nodo location;
        info = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE EL NODO PRINCIPAL AL QUE QUIERE AGREGAR"));
        location = Locate(info);
        if (location == null) {
            System.out.println("No se encontro el nodo");
            return false;
        }
        String family = location.info + ": ";
        NodoFamilia aux = location.family;
        while (aux != null) {
            family += aux.info + " -> ";
            aux = aux.next;
        }
        family += " ->  null";

        System.out.println(family);
        return true;
    }

    public boolean printAll() {
        System.out.println("TODA LA LISTA");
        if (pri == null) {
            System.out.println("COLA PRINCIPAL VACIA");
            return false;
        }
        Nodo aux = pri;
        while (aux != null) {
            String family = aux.info + ": ";
            NodoFamilia auxFamilia = aux.family;
            while (auxFamilia != null) {
                family += auxFamilia.info + " -> ";
                auxFamilia = auxFamilia.next;
            }
            ordenarColaPrincipal();
            family += " ->  null";
            System.out.println(family);
            aux = aux.next;
        }

        return true;
    }

    public boolean delete(int info) {
        if (pri == null) {
            System.out.println("COLA PRINCIPAL VACIA");
            return false;
        }
        if (info == 0) {
            info = Integer.parseInt(JOptionPane.showInputDialog(null, "INGRESE Al DATO A ELIMINAR"));
        }
        Nodo aux = Locate(info);
        if (aux != null) {
            if (aux == pri) {
                pri = pri.next;
                return true;
            }

            if (pri.next == ult) {
                pri.next = null;
                ult.family = null;
                ult = pri;
            }

            Nodo before = pri;
            while (before.next != aux) {
                before = before.next;
            }
            before.next = aux.next;
            return true;
        }

        Nodo auxFamilia = LocateFamily(info);
        if (auxFamilia == null) {
            System.out.println("No se encontro el dato");
            return false;
        }

        NodoFamilia auxDelete = auxFamilia.family;
        if (auxDelete.next == null) {
            auxFamilia.family = null;
            System.out.println("Se elimino el unico elemento de la familia");
            printAll();
            return true;
        }
        NodoFamilia before = auxFamilia.family;
        while (auxDelete.info != info) {
            before = auxDelete;
            auxDelete = auxDelete.next;
        }

        before.next = auxDelete.next;
        printAll();

        return true;
    }

    public void vaciar() {
        pri = null;
        ult = null;
    }

    public static void main(String[] ar) {
        int Opcion;
        ListaMultiple listaMultiple = new ListaMultiple();
        do {
            Opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "1. INGRESAR DATOS\n" +
                            "2. INSERTAR FAMILY\n" +
                            "3. LISTAR COLA PRINCIPAL\n" +
                            "4. LISTAR FAMILIA\n" +
                            "5. LISTAR TODO\n" +
                            "6. ELIMINAR DATO\n" +
                            "7. VACIAR \n" +
                            "8. SALIR\n" +
                            "--------------------------------------------------------\n" +
                            "INGRESE LA OPCION [1 - 8]",
                    "MENU Cola", JOptionPane.QUESTION_MESSAGE));

            switch (Opcion) {
                case 1:
                    listaMultiple.insertar();
                    break;
                case 2:
                    listaMultiple.insertFamily();
                    break;
                case 3:
                    listaMultiple.printMain();
                    break;
                case 4:
                    listaMultiple.printFamily();
                    break;
                case 5:
                    listaMultiple.printAll();
                    break;
                case 6:
                    listaMultiple.delete(0);
                    break;
                case 7:
                    listaMultiple.vaciar();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "INGRESE UNA OPCION VALIDA \n", "ERROR OPCION",
                            JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while (true);
    }

}
