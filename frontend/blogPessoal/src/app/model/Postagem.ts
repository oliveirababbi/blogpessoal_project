import { Categoria } from "./Categoria"
import { usuario } from "./usuario"

export class Postagem {
    public id: number
    public title: string
    public text: string
    public date: Date
    public usuario: usuario
    public categoria: Categoria
}