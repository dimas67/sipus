/*
 * Copyright (C) 2011 Dimas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sipus.dao;

import java.util.List;
import sipus.model.Anggota;

/**
 *
 * @author Dimas
 */
public interface AnggotaDao {

    public void insert(Anggota anggota);

    public void update(Anggota anggota);

    public void delete(Anggota anggota);

    public Anggota getAnggota(String anggota);

    public List<Anggota> getAll();

    public List<Anggota> getAll(String keyword);

    public String getGeneratedNumber();
    
    public void updateStatus(Anggota anggota);

    public boolean isSuccess();
}
