import { Movimento } from "./movimento";

export interface Lancamentos{
    idLancamento:any,
    tipo:String,
    descricao:String,
    valor:Number,
    movimento: Movimento;
}