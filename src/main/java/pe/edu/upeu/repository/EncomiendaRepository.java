package pe.edu.upeu.repository;

import pe.edu.upeu.enums.EstadoEncomienda;
import pe.edu.upeu.model.Encomienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EncomiendaRepository {

    public boolean guardar(Encomienda encomienda) {

        String sql = """
            INSERT INTO encomienda
            (
                codigo,
                remitente,
                destinatario,
                origen,
                destino,
                descripcion,
                peso,
                precio,
                estado,
                fecha_envio
            )
            VALUES (?,?,?,?,?,?,?,?,?,?)
            """;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, encomienda.getCodigo());
            ps.setString(2, encomienda.getRemitente());
            ps.setString(3, encomienda.getDestinatario());
            ps.setString(4, encomienda.getOrigen());
            ps.setString(5, encomienda.getDestino());
            ps.setString(6, encomienda.getDescripcion());
            ps.setDouble(7, encomienda.getPeso());
            ps.setDouble(8, encomienda.getPrecio());
            ps.setString(9, encomienda.getEstado().name());
            ps.setString(10, encomienda.getFechaEnvio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Encomienda encomienda) {

        String sql = """
            UPDATE encomienda
            SET codigo = ?,
                remitente = ?,
                destinatario = ?,
                origen = ?,
                destino = ?,
                descripcion = ?,
                peso = ?,
                precio = ?,
                estado = ?,
                fecha_envio = ?
            WHERE id = ?
            """;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, encomienda.getCodigo());
            ps.setString(2, encomienda.getRemitente());
            ps.setString(3, encomienda.getDestinatario());
            ps.setString(4, encomienda.getOrigen());
            ps.setString(5, encomienda.getDestino());
            ps.setString(6, encomienda.getDescripcion());
            ps.setDouble(7, encomienda.getPeso());
            ps.setDouble(8, encomienda.getPrecio());
            ps.setString(9, encomienda.getEstado().name());
            ps.setString(10, encomienda.getFechaEnvio());
            ps.setInt(11, encomienda.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizarEstado(int id, String estado) {

        String sql =
                "UPDATE encomienda SET estado = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int id) {

        String sql =
                "DELETE FROM encomienda WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Encomienda> listar() {

        List<Encomienda> lista = new ArrayList<>();

        String sql = "SELECT * FROM encomienda";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Encomienda e = new Encomienda();

                e.setId(rs.getInt("id"));
                e.setCodigo(rs.getString("codigo"));
                e.setRemitente(rs.getString("remitente"));
                e.setDestinatario(rs.getString("destinatario"));
                e.setOrigen(rs.getString("origen"));
                e.setDestino(rs.getString("destino"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setPeso(rs.getDouble("peso"));
                e.setPrecio(rs.getDouble("precio"));

                e.setEstado(
                        EstadoEncomienda.valueOf(
                                rs.getString("estado")
                        )
                );

                e.setFechaEnvio(
                        rs.getString("fecha_envio")
                );

                lista.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Encomienda buscarPorCodigo(String codigo) {

        String sql =
                "SELECT * FROM encomienda WHERE codigo = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Encomienda e = new Encomienda();

                e.setId(rs.getInt("id"));
                e.setCodigo(rs.getString("codigo"));
                e.setRemitente(rs.getString("remitente"));
                e.setDestinatario(rs.getString("destinatario"));
                e.setOrigen(rs.getString("origen"));
                e.setDestino(rs.getString("destino"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setPeso(rs.getDouble("peso"));
                e.setPrecio(rs.getDouble("precio"));

                e.setEstado(
                        EstadoEncomienda.valueOf(
                                rs.getString("estado")
                        )
                );

                e.setFechaEnvio(
                        rs.getString("fecha_envio")
                );

                return e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int contarTotal() {

        String sql =
                "SELECT COUNT(*) FROM encomienda";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int contarPorEstado(String estado) {

        String sql =
                "SELECT COUNT(*) FROM encomienda WHERE estado = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setString(1, estado);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}