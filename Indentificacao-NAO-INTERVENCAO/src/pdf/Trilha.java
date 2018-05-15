package pdf;

import javafx.geometry.Point3D;

import java.util.Collection;
import java.util.LinkedList;

public class Trilha {

    private final LinkedList<Point3D> pontos = new LinkedList<Point3D>();
    public Trilha() {}
    public Trilha(Collection<Point3D> pontos) {
        pontos.addAll(pontos);
    }
    public double addPonto(long x, long y, long z) {
        return addPonto(new Point3D(x,y,z));
    }
    public double addPonto(Point3D point) {
        Point3D start = (pontos.size() > 0) ? pontos.getLast() : point;
        //GeometricVector nextDistance = new GeometricVector(start, point);
        pontos.add(point);
        //return nextDistance.size();
    }
    public double addPontos(Trilha trilha) {
        this.pontos.addAll(trilha.pontos);
        return trilha.distancia();
    }
    public double addPontos(Collection<Point3D> pontos) {
        return addPontos(new Trilha(pontos));
    }
    public boolean removePrimeiraOcorrencia(Point3D ponto) {
        return pontos.removeFirstOccurrence(ponto);
    }
    public boolean removeUltimaOcorrencia(Point3D ponto) {
        return pontos.removeLastOccurrence(ponto);
    }
    public boolean removeOcorrenciaN(Point3D ponto, int n) {
        boolean modifie = false;
        //if ( n <= Collections.frequency(pontos, ponto) ) {
            synchronized(pontos) {
                int count = 0;
                //for(Iterator<Point3D> itr = pontos.iterator(); itr.hasNext();) {
                    //Point3D p = itr.next();
                    //if (ponto.equals(p))
                        count++;
                    if (count == n) {
                        //itr.remove();
                        //break;
                    }
                }
            }
        }
        return modifie;
    }
    public synchronized boolean removeSubTrilha(Trilha trilha) {
        int start = Collections.indexOfSubList(pontos, trilha.pontos);
        if (start == -1)
            return false;
        for (int i = start; i < trilha.pontos.size(); i++)
            pontos.remove(i);
        return true;
    }
    public double distancia() {
        if (pontos.size() < 2)
            return 0D;
        double distancia = 0D;
        Point3D start = pontos.getFirst();
        Point3D end;
        for (Point3D p : pontos) {
            end = p;
            distancia += (new GeometricVector(start, end)).size();
            start = p;
        }

            return distancia;

        }
    }
}
