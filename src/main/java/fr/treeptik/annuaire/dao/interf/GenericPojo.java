package fr.treeptik.annuaire.dao.interf;

public interface GenericPojo<Pk> {

	Pk getId();
	
}

/*ajouter @Override sur le getter de l'id de chaque pojo
ajouter "extend GenericPojo" sur le T de GenericDao et de GenericJPADAO"
les methodes generic prenant en args l'id pourront etre utilisées avec entity.getId()
*/