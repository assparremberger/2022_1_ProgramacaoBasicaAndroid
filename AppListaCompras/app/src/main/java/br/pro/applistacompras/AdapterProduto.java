package br.pro.applistacompras;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class AdapterProduto extends BaseAdapter {

    private List<Produto> lista;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produto> lista){
        this.lista = lista;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get( i );
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemSuporte item;
        if( view == null ){
            view = inflater.inflate(R.layout.item_lista, null);
            item = new ItemSuporte();
            item.tvNome = view.findViewById(R.id.tvNomeProduto);
            item.tvQtd = view.findViewById(R.id.tvQuantidadeProduto);
            item.layout = view.findViewById(R.id.llProduto);
            view.setTag( item );
        }else {
            item = (ItemSuporte) view.getTag();
        }
        Produto produto = lista.get( i );
        item.tvNome.setText( produto.getNome() );
        item.tvQtd.setText( String.valueOf( produto.getQuantidade() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        return view;
    }

    private class ItemSuporte{
        TextView tvNome, tvQtd;
        LinearLayout layout;
    }
}
