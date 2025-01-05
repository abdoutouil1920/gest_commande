package com.gestcomm.gestcomm.Service;

import com.gestcomm.gestcomm.Model.Rapport;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {

    public Rapport generateRapport() {
        // Exemple de données statiques, remplacez par des données dynamiques
        return new Rapport(156, 142, 14, 12450.0);
    }
}
