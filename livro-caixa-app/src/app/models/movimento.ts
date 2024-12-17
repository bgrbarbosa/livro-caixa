import { Lancamentos } from "./lancamentos";

export interface Movimento{
    idMovimento?:any,
    data:String,
    obs:String,
    status:String,
    lancamentos?:Lancamentos[],
    totalCaixa?:Number
}



