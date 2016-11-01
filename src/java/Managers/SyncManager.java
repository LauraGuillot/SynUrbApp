package Managers;

import Classes.Data;

public interface SyncManager {

    /**
     * Mise à jour des données dans la base distantes : mise à jour des lignes
     * qui existent déjà ou insertion de nouvelles lignes
     *
     * @param d Ensemble des données à mettre à jour
     * @return Id du projet mis à jour (ou ajouté)
     */
    public long UpdateData(Data d);
    
    
}
