package br.com.dbserver.lista.agendavacina.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.dbserver.lista.agendavacina.databinding.ItemGrupoPrioritarioSelectBinding
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario

class GrupoPrioritarioAdapter(var gruposPrioritarios: List<GrupoPrioritario>): RecyclerView.Adapter<GrupoPrioritarioAdapter.GrupoPrioritarioViewHolder>() {
    val selectedIndexes = ArrayList<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrupoPrioritarioViewHolder {
        var binding = ItemGrupoPrioritarioSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GrupoPrioritarioViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gruposPrioritarios.size
    }

    override fun onBindViewHolder(holder: GrupoPrioritarioViewHolder, position: Int) {
        holder.bind(gruposPrioritarios[position], position)
    }

    fun getSelectedItens():List<GrupoPrioritario>{
        return gruposPrioritarios.filterIndexed { index, comorbidade -> index in selectedIndexes  }
    }

    inner class GrupoPrioritarioViewHolder(val binding: ItemGrupoPrioritarioSelectBinding): RecyclerView.ViewHolder(binding.root){
        var selected = MutableLiveData(false)
        var index: Int = -1
        fun bind(gp: GrupoPrioritario, i: Int){
            binding.item = gp
            binding.clickHandler = this
            index = i
        }

        fun handleCardClick(){
            binding.itemGrupoPrioritarioCard.apply {
                binding.itemGrupoPrioritarioSelectCheckBox.performClick()
                isSelected = binding.itemGrupoPrioritarioSelectCheckBox.isSelected
                selected.value = isSelected
                if (isSelected) selectedIndexes.add(index) else selectedIndexes.remove(index)
            }
        }

        fun handleCheckboxClick(){
            binding.itemGrupoPrioritarioSelectCheckBox.apply {
                binding.itemGrupoPrioritarioCard.isChecked = isChecked
                isSelected = isChecked
                selected.value = isChecked
                if (isSelected) selectedIndexes.add(index) else selectedIndexes.remove(index)
            }
        }


    }
}