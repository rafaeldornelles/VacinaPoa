package br.com.dbserver.lista.agendavacina.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import br.com.dbserver.lista.agendavacina.databinding.ItemComorbidadeSelectBinding
import br.com.dbserver.lista.agendavacina.model.Comorbidade

class ComorbidadeAdapter(var comorbidades: List<Comorbidade>): RecyclerView.Adapter<ComorbidadeAdapter.ComorbidadeViewHolder>() {
    val selectedIndexes = ArrayList<Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComorbidadeViewHolder {
        var binding = ItemComorbidadeSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComorbidadeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comorbidades.size
    }

    override fun onBindViewHolder(holder: ComorbidadeViewHolder, position: Int) {
        holder.bind(comorbidades[position], position)
    }

    fun getSelectedItens():List<Comorbidade>{
        return comorbidades.filterIndexed { index, comorbidade -> index in selectedIndexes  }
    }

    inner class ComorbidadeViewHolder(val binding: ItemComorbidadeSelectBinding): RecyclerView.ViewHolder(binding.root){
        var selected = MutableLiveData(false)
        var index: Int = -1
        fun bind(c: Comorbidade, i: Int){
            binding.item = c
            binding.clickHandler = this
            index = i
        }

        fun handleCardClick(){
            binding.itemComorbidadeCard.apply {
                binding.itemComorbidadeSelectCheckBox.performClick()
                isSelected = binding.itemComorbidadeSelectCheckBox.isSelected
                selected.value = isSelected
                if (isSelected) selectedIndexes.add(index) else selectedIndexes.remove(index)
                binding.notifyChange()
            }
        }

        fun handleCheckboxClick(){
            binding.itemComorbidadeSelectCheckBox.apply {
                binding.itemComorbidadeCard.isChecked = isChecked
                isSelected = isChecked
                selected.value = isChecked
                if (isSelected) selectedIndexes.add(index) else selectedIndexes.remove(index)
                binding.notifyChange()
            }
        }


    }
}