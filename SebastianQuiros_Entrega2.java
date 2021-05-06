import java.io.*;

class SebastianQuiros_Entrega2{
    
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = new PrintStream(System.out);
    static final double RECUPERABILIDAD = 0.05;
    static final double HOSPI_PORCENTAJE = 0.014;

    public static void main(String[] args) throws IOException {
        String pais;
        double R, transmisibilidad, vecinos, nuevos, recuperados, transmisores, activosDouble, hospitalizadosDouble;
        int contador, totalDias, activos, hospitalizados;

        out.print("Introduzca el nombre del país: ");
        pais = in.readLine();

        out.print("Introduzca el número de días a simular: ");
        totalDias = Integer.parseInt(in.readLine());

        out.print("Introduzca número de casos activos iniciales: ");
        activosDouble = Double.parseDouble(in.readLine());

        out.print("Introduzca número efectivo reproductivo: ");
        R = Double.parseDouble(in.readLine());

        out.print("Introduzca número de contagios por visita de países vecinos: ");
        vecinos = Double.parseDouble(in.readLine());

        out.print("Introduzca tasa de transmisibilidad: ");
        transmisibilidad = Double.parseDouble(in.readLine());

        if(transmisibilidad > 0 && transmisibilidad <= 1 && totalDias > 0 && activosDouble > 0 && R > 0 && vecinos >= 0){
            hospitalizadosDouble = Math.ceil(activosDouble*HOSPI_PORCENTAJE);
            hospitalizados = (int) hospitalizadosDouble;
            activos = (int) activosDouble;
            out.println("\n"+pais+": ");
            out.println("Día    A     H");
            out.println("1      "+activos+"    "+hospitalizados);
            for(contador = 2, nuevos = 0, recuperados = 0, hospitalizadosDouble= 0, transmisores= 0; contador <= totalDias; contador++){
                recuperados = activosDouble*RECUPERABILIDAD;
                hospitalizadosDouble = Math.ceil(activos*HOSPI_PORCENTAJE);
                transmisores = activosDouble - hospitalizadosDouble;
                nuevos = transmisores*transmisibilidad*R;
                activosDouble = Math.ceil(activos + nuevos - recuperados + vecinos);
                activos = (int)activosDouble;
                hospitalizados = (int) hospitalizadosDouble;
                out.println(contador+"      "+activos+"    "+hospitalizados);
            }     
        }
        else{
            out.println("Inserte datos válidos (transmisilibilidad no mayor a 1) (No números negativos).");
        }
    }
}        