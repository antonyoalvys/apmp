package br.com.apmp.ccompras.beans.search;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.LazyDataModel;

import br.com.apmp.ccompras.domain.exceptions.RepositoryException;
import br.com.apmp.ccompras.service.exceptions.ServiceException;

/**
 *
 * @author antoniocarvalho
 */
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.faces.component.UIComponent;
//
//import org.primefaces.component.datatable.DataTable;
//import org.primefaces.component.tabview.Tab;
//import org.primefaces.component.tabview.TabView;
//import org.primefaces.context.RequestContext;
//import org.primefaces.event.TabCloseEvent;
//import org.primefaces.model.LazyDataModel;
//import org.primefaces.model.SortOrder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
public abstract class BaseBeanSearch<T> implements Serializable {

    private static final long serialVersionUID = -1236184169177912409L;

    private T searchEntity;

    private T editEntity;

    private T showEntity;

    private T removeEntity;

    private final Class<T> searchEntityClass;

    private LazyDataModel<T> dataModel;

    private DataTable searchResults;

    private Tab showTab;

    private Tab editTab;

    private Tab cancelTab;

    private Tab disableTab;
    
    public BaseBeanSearch() {
        this.searchEntityClass = searchEntityClass();
        newSearchEntity();
    }

    public void newSearchEntity() {
        try {
            searchEntity = searchEntityClass.newInstance();
        } catch (Exception e) {
            // FIXME: handle exception
        }
    }

    public T getSearchEntity() {
        return searchEntity;
    }

    public void setSearchEntity(T searchEntity) {
        this.searchEntity = searchEntity;
    }

    public T getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(T editEntity) {
        this.editEntity = editEntity;
    }

    public T getShowEntity() {
        return showEntity;
    }

    public void setShowEntity(T showEntity) {
        this.showEntity = showEntity;
    }

    public T getRemoveEntity() {
        return removeEntity;
    }

    public void setRemoveEntity(T removeEntity) {
        this.removeEntity = removeEntity;
    }

    public DataTable getSearchResults() {
        return searchResults;
    }

    private void setUpDataTable(DataTable table) {
        ///table.setLazy(true);
        table.setPaginator(true);
        table.setPaginatorPosition("bottom");
        ///table.setEmptyMessage( ResourceBundleUtils.getLocalizedMessage( "table.emptyMessage" ) );
        ///table.setRows( Integer.parseInt( ResourceBundleUtils.getLocalizedMessage( "table.rows" ) ) );
        table.setVar("item");
        ///table.setRowsPerPageTemplate( ResourceBundleUtils.getLocalizedMessage( "table.rowsPerPageTemplate" ) );
        ///table.setPaginatorTemplate( ResourceBundleUtils.getLocalizedMessage( "table.paginatorTemplate" ) );
        ///table.setCurrentPageReportTemplate( ResourceBundleUtils.getLocalizedMessage( "table.currentPageReportTemplate" ) );
    }

    public void onTabClose(TabCloseEvent event) {
        closeTab(event.getTab());
    }

    public void closeTab(Tab tab) {
        TabView parent = (TabView) tab.getParent();
        tab.setRendered(false);
        parent.setActiveIndex(0);
        complementOnTabClose(tab);
    }

    public void complementOnTabClose(Tab tab) {
        try {
            if (tab.equals(showTab)) {
                showEntity = searchEntityClass.newInstance();
            }

            if (tab.equals(editTab)) {
                editEntity = searchEntityClass.newInstance();
                RequestContext.getCurrentInstance().reset(":" + tab.getParent().getId() + ":editForm");
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setSearchResults(DataTable searchResults) {
        this.searchResults = searchResults;
        setUpDataTable(this.searchResults);
    }

    public LazyDataModel<T> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<T> dataModel) {
        this.dataModel = dataModel;
    }

    public void find() {
        loadModel();
        DataTable results = this.getSearchResults();
        if (results != null) {
            results.setFirst(0);
        }
    }

    private void loadModel() {
        setDataModel(new LazyDataModel<T>() {

            private static final long serialVersionUID = -7141739005508115863L;

            ///public List<T> load1(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
           @Override
            public List<T> load(int first, int pageSize, String sortField, org.primefaces.model.SortOrder sortOrder, Map<String, Object> filters) {
                List<T> results = null;
                try {
                    results = getList();
                } catch (ServiceException ex) {
                    Logger.getLogger(BaseBeanSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RepositoryException ex) {
                    Logger.getLogger(BaseBeanSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                return results;
            }

            @Override
            public void setRowIndex(int rowIndex) {
                /*
                 * The following is in ancestor (LazyDataModel): this.rowIndex =
                 * rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                 */
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                } else {
                    super.setRowIndex(rowIndex % getPageSize());
                }
            }

        });
    }

    public void edit() {
        TabView parent = (TabView) editTab.getParent();
        parent.setActiveIndex(1);
        editTab.setRendered(true);
    }

    public void show() {
        TabView parent = (TabView) showTab.getParent();
        parent.setActiveIndex(verificarIndex(showTab.getId()));
        showTab.setRendered(true);
    }

    public void loadToCancel() {
        TabView parent = (TabView) cancelTab.getParent();
        parent.setActiveIndex(verificarIndex(cancelTab.getId()));
        cancelTab.setRendered(true);
    }

    public void loadToDisable() {
        TabView parent = (TabView) disableTab.getParent();
        parent.setActiveIndex(verificarIndex(disableTab.getId()));
        disableTab.setRendered(true);
    }

    private int verificarIndex(String operacao) {
        if (operacao != null) {
            if (editTab != null && cancelTab != null && showTab != null && disableTab != null) {
                return verificarOrdemTresOperacoes(operacao);
            } else {
                return verificarOrdemDuasOperacoes(operacao);
            }
        } else {
            return 0;
        }
    }

    private int verificarOrdemTresOperacoes(String operacao) {
        if (operacao.equals("cancelTab")) {
            if ((editTab.isRendered() && cancelTab.isRendered() && showTab.isRendered()) || (cancelTab.isRendered() && editTab.isRendered()) || (editTab.isRendered())) {
                return 2;
            } else {
                return 1;
            }
        }

        if (operacao.equals("showTab")) {
            if ((editTab.isRendered() && cancelTab.isRendered() && showTab.isRendered()) || (cancelTab.isRendered() && editTab.isRendered())) {
                return 3;
            } else if (cancelTab.isRendered() || editTab.isRendered()) {
                return 2;
            } else {
                return 1;
            }
        }
        return 1;
    }

    private int verificarOrdemDuasOperacoes(String operacao) {
        if (operacao.equals("cancelTab")) {
            return 1;
        }

        if ((cancelTab != null && cancelTab.isRendered()) || (showTab != null && showTab.isRendered())) {
            return 2;
        } else {
            return 1;
        }
    }

    public Tab getShowTab() {
        return showTab;
    }

    public void setShowTab(Tab showTab) {
        this.showTab = showTab;
    }

    public Tab getEditTab() {
        return editTab;
    }

    public void setEditTab(Tab editTab) {
        this.editTab = editTab;
    }

    public Tab getCancelTab() {
        return cancelTab;
    }

    public void setCancelTab(Tab cancelTab) {
        this.cancelTab = cancelTab;
    }

    public Tab getDisableTab() {
        return disableTab;
    }

    public void setDisableTab(Tab disableTab) {
        this.disableTab = disableTab;
    }

    protected abstract List<T> getList() throws ServiceException, RepositoryException ;
    
    protected abstract Class<T> searchEntityClass();

}