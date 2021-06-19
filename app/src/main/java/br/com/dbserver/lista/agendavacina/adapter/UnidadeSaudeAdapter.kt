package br.com.dbserver.lista.agendavacina.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dbserver.lista.agendavacina.databinding.ItemUnidadeSaudeBinding
import br.com.dbserver.lista.agendavacina.model.UnidadeSaude

class UnidadeSaudeAdapter(var unidadesSaude: List<UnidadeSaude>, val handler: CardClickHandler): RecyclerView.Adapter<UnidadeSaudeAdapter.UnidadeSaudeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnidadeSaudeViewHolder {
        var binding = ItemUnidadeSaudeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UnidadeSaudeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return unidadesSaude.size
    }

    override fun onBindViewHolder(holder: UnidadeSaudeViewHolder, position: Int) {
        holder.bind(unidadesSaude[position])
    }


    inner class UnidadeSaudeViewHolder(val binding: ItemUnidadeSaudeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(us: UnidadeSaude){
            binding.item = us
            binding.clickHandler = handler
        }

    }

    interface CardClickHandler{
        fun onCardClick(us: UnidadeSaude)
    }
}